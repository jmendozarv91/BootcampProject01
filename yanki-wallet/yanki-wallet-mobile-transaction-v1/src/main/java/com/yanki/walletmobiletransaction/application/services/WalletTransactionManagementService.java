package com.yanki.walletmobiletransaction.application.services;

import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.application.events.TransactionEventType;
import com.yanki.walletmobiletransaction.application.mapper.WalletTransactionEventMapper;
import com.yanki.walletmobiletransaction.application.mapper.WalletTransactionRequestMapper;
import com.yanki.walletmobiletransaction.application.mapper.WalletTransactionResponseMapper;
import com.yanki.walletmobiletransaction.application.usecases.WalletTransactionService;
import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.constants.WalletTransactonConstant;
import com.yanki.walletmobiletransaction.domain.model.dto.CreditWalletRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.DebitCardLinkResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.LinkDebitCardRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionStatusUpdateRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletBalanceResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import com.yanki.walletmobiletransaction.domain.model.enums.TransactionStatus;
import com.yanki.walletmobiletransaction.domain.port.WalletLinkedBankTxEventPort;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionEventPort;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionPersistencePort;
import com.yanki.walletmobiletransaction.infraestructure.adapters.exception.TransactionException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Log4j2
public class WalletTransactionManagementService implements WalletTransactionService {

  private final WalletTransactionPersistencePort walletTransactionPersistencePort;
  private final WalletTransactionEventPort walletTransactionEventPort;
  private final WalletTransactionRequestMapper walletTransactionRequestMapper;
  private final WalletTransactionResponseMapper walletTransactionResponseMapper;
  private final WalletTransactionEventMapper walletTransactionEventMapper;
  private final WalletLinkedBankTxEventPort walletLinkedBankTxEventPort;

  @Override
  public Mono<WalletBalanceResponse> creditWalletToBankAccount(String walletId,
      CreditWalletRequest creditWalletRequest) {
    return null;
  }

  @Override
  public Mono<WalletBalanceResponse> loadWalletFromDebitCard(String walletId) {
    return null;
  }

  @Override
  public Mono<DebitCardLinkResponse> linkDebitCard(LinkDebitCardRequest linkDebitCardRequest) {
    // Mapear la solicitud de vinculación de tarjeta de débito a una transacción de dominio
    Transaction transaction = walletTransactionRequestMapper.linkedRequestToDomain(linkDebitCardRequest);

    // Persistir la transacción en la base de datos y luego realizar otras operaciones
    return walletTransactionPersistencePort.linkDebitCard(transaction)
        .then(Mono.defer(() -> {
          // Crear el evento de vinculación de tarjeta de débito
          TransactionEvent debitCardLinkedEvent = walletTransactionEventMapper.mapToDebitCardLinkedEvent(transaction);

          // Publicar el evento y esperar a que se complete la publicación
          return Mono.fromRunnable(() -> {
            walletLinkedBankTxEventPort.publishDebitCardLinkedEvent(
                debitCardLinkedEvent.getSourceWalletId(),
                debitCardLinkedEvent.getDebitCardNumber(),
                debitCardLinkedEvent.getBankAccountId());
          }).thenReturn(debitCardLinkedEvent);
        }))
        .map(debitCardLinkedEvent -> {
          // Crear la respuesta con los datos necesarios
          DebitCardLinkResponse debitCardLinkResponse = new DebitCardLinkResponse();
          debitCardLinkResponse.setDebitCardNumber(debitCardLinkedEvent.getDebitCardNumber());
          debitCardLinkResponse.setWalletId(debitCardLinkedEvent.getSourceWalletId());
          debitCardLinkResponse.setMessage(WalletTransactonConstant.DEBIT_CARD_MESSAGE_LINKED_SUCESS);
          return debitCardLinkResponse;
        });
  }

  @Override
  public Mono<TransactionResponse> transferWalletToWallet(
      WalletTransferRequest walletTransferRequest) {
    // Paso 1: Mapea el WalletTransferRequest a una entidad de transacción (si es necesario)
    Transaction transactionEntity = walletTransactionRequestMapper.walletTransferRequestToDomain(
        walletTransferRequest);
    // Paso 2: Persiste la transacción en la base de datos
    return walletTransactionPersistencePort.create(transactionEntity)
        .flatMap(savedTransaction -> {
          publishWalletToWalletTransferEvent(walletTransferRequest);
          // Paso 4: Mapea la entidad de transacción a una respuesta de transacción
          TransactionResponse transactionResponse = walletTransactionResponseMapper.toTransactionResponse(
              savedTransaction);
          return Mono.just(transactionResponse);
        });
  }


  @Override
  public Mono<TransactionResponse> getTransactionById(String transactionId) {
    return walletTransactionPersistencePort.getById(transactionId)
        .map(walletTransactionResponseMapper::toDto)
        .switchIfEmpty(Mono.error(new TransactionException(HttpStatus.BAD_REQUEST,
            String.format(WalletTransactonConstant.CURREN_TRANSACTION_NO_FOUND, transactionId))));
  }

  @Override
  public Mono<TransactionResponse> createTransaction(TransactionRequest transactionRequest) {
    return Mono.just(transactionRequest)
        .filter(request -> request.getAmount() > 0)
        .flatMap(request -> {
          Transaction transaction = walletTransactionRequestMapper.toDomain(request);
          transaction.setType(TransactionEventType.CREATE.name());
          return walletTransactionPersistencePort.create(transaction);
        })
        .flatMap(savedTransaction -> {
          walletTransactionEventPort.publishTransactionEvent(
              walletTransactionEventMapper.toEvent(savedTransaction));
          return Mono.just(walletTransactionResponseMapper.toTransactionResponse(
              savedTransaction));
        });
  }

  @Override
  public Mono<TransactionResponse> cancelTransaction(String transactionId) {
    return walletTransactionPersistencePort.getById(transactionId)
        .filter(transaction -> TransactionStatus.CREATE.name().equals(transaction.getType()))
        .flatMap(transaction -> {
          log.info(transaction);
          return Mono.fromRunnable(() -> {
                walletTransactionPersistencePort.cancelTransaction(transaction);
                walletTransactionEventPort.publishTransactionCanceledEvent(transaction.getId());
              })
              .thenReturn(walletTransactionResponseMapper.toTransactionResponse(transaction));
        });
  }





  @Override
  public Flux<TransactionResponse> getTransactionsByUserId(String userId) {
    return null;
  }

  @Override
  public Mono<WalletBalanceResponse> getWalletBalance(String walletId) {
    return null;
  }


  @Override
  public Flux<TransactionResponse> listWalletTransactions(String walletId) {
    return null;
  }



  @Override
  public Mono<TransactionResponse> updateTransactionStatus(String transactionId,
      TransactionStatusUpdateRequest transactionStatusUpdateRequest) {
    return null;
  }

  private void publishWalletToWalletTransferEvent(WalletTransferRequest walletTransferRequest) {
    walletTransactionEventPort.publishWalletToWalletTransferEvent(
        walletTransferRequest.getSourceWalletId(),
        walletTransferRequest.getTargetPhoneNumber(),
        walletTransferRequest.getAmount()
    );
  }
}
