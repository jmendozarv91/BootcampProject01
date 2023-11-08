package com.yanki.walletmobiletransaction.application.services;

import com.yanki.walletmobiletransaction.application.mapper.WalletTransactionRequestMapper;
import com.yanki.walletmobiletransaction.application.mapper.WalletTransactionResponseMapper;
import com.yanki.walletmobiletransaction.application.usecases.WalletTransactionService;
import com.yanki.walletmobiletransaction.domain.model.dto.CreditWalletRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.DebitCardLinkResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.LinkDebitCardRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionStatusUpdateRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletBalanceResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionEventPort;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

  @Override
  public Mono<TransactionResponse> cancelTransaction(String transactionId) {
    return null;
  }

  @Override
  public Mono<TransactionResponse> createTransaction(TransactionRequest transactionRequest) {
      return null;
  }

  @Override
  public Mono<WalletBalanceResponse> creditWalletToBankAccount(String walletId,
      CreditWalletRequest creditWalletRequest) {
    return null;
  }

  @Override
  public Mono<TransactionResponse> getTransactionById(String transactionId) {
    return null;
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
  public Mono<DebitCardLinkResponse> linkDebitCard(LinkDebitCardRequest linkDebitCardRequest) {
    return null;
  }

  @Override
  public Flux<TransactionResponse> listWalletTransactions(String walletId) {
    return null;
  }

  @Override
  public Mono<WalletBalanceResponse> loadWalletFromDebitCard(String walletId) {
    return null;
  }

  @Override
  public Mono<TransactionResponse> transferWalletToWallet(
      WalletTransferRequest walletTransferRequest) {
    return null;
  }

  @Override
  public Mono<TransactionResponse> updateTransactionStatus(String transactionId,
      TransactionStatusUpdateRequest transactionStatusUpdateRequest) {
    return null;
  }
}
