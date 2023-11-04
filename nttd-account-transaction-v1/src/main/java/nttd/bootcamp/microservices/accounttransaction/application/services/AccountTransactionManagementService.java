package nttd.bootcamp.microservices.accounttransaction.application.services;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.accounttransaction.application.mapper.AccountTransactionRequestMapper;
import nttd.bootcamp.microservices.accounttransaction.application.mapper.AccountTransactionResponseMapper;
import nttd.bootcamp.microservices.accounttransaction.application.usecases.AccountTransactionService;
import nttd.bootcamp.microservices.accounttransaction.domain.model.Transaction;
import nttd.bootcamp.microservices.accounttransaction.domain.model.constants.TransactionConstant;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.Account;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferResponse;
import nttd.bootcamp.microservices.accounttransaction.domain.model.enums.TransactionType;
import nttd.bootcamp.microservices.accounttransaction.domain.port.AccountServicePort;
import nttd.bootcamp.microservices.accounttransaction.domain.port.AccountTransactionPersistencePort;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity.TransferDetails;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.AccountTransactionException;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AccountTransactionManagementService implements AccountTransactionService {

  private final AccountTransactionPersistencePort accountTransactionPersistencePort;
  private final AccountTransactionResponseMapper accountTransactionResponseMapper;
  private final AccountTransactionRequestMapper accountTransactionRequestMapper;
  private final AccountServicePort accountServicePort;


  @Override
  public Flux<TransactionResponse> getAccountTransactions(String ownerId) {
    return accountTransactionPersistencePort.getTransactionsByOwnerId(ownerId)
        .map(accountTransactionResponseMapper::toDtoWithTransfer);
  }

  @Override
  public Mono<TransactionResponse> depositTransaction(String accountId,
      TransactionRequest request) {
    return processTransaction(accountId, request, TransactionType.DEPOSIT);
  }

  @Override
  public Mono<TransactionResponse> withdrawalTransaction(String accountId,
      TransactionRequest request) {
    return processTransaction(accountId, request, TransactionType.WITHDRAWAL);
  }

  private Mono<TransactionResponse> processTransaction(String accountId, TransactionRequest request,
      TransactionType transactionType) {
    return accountServicePort.findAccountById(accountId)
        .flatMap(account -> {
          handleCommission(account);
          return updateAccountBalance(account, request.getAmount(), transactionType)
              .flatMap(updatedAccount -> createTransaction(account, transactionType,
                  request.getAmount())
                  .flatMap(savedTransaction -> accountServicePort.modifyAccountBalance(accountId,
                          updatedAccount)
                      .thenReturn(savedTransaction)))
              .map(accountTransactionResponseMapper::toDto);
        });
  }


  @Override
  public Mono<TransferResponse> makeTransfer(TransferRequest request) {
    // Realiza una transferencia entre cuentas.
    return accountServicePort.findAccountById(request.getFromAccountId())
        .flatMap(fromAccount -> {
          if (fromAccount.getBalance() < request.getAmount()) {
            return Mono.error(
                new AccountTransactionException(HttpStatus.BAD_REQUEST,
                    TransactionConstant.INSUFFICIENT_FUNDS));
          }
          return accountServicePort.findAccountById(request.getToAccountId())
              .flatMap(toAccount -> {
                if (!fromAccount.getBankId().equals(toAccount.getBankId())) {
                  return Mono.error(new AccountTransactionException(HttpStatus.BAD_REQUEST,
                      TransactionConstant.INVALID_TRANSFER_BETWEEN_DIFFERENT_BANKS));
                }
                fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                return accountServicePort.modifyAccountBalance(fromAccount.getId(), fromAccount)
                    .then(accountServicePort.modifyAccountBalance(toAccount.getId(), toAccount))
                    .then(accountTransactionPersistencePort.create(
                        Transaction.builder()
                            .transferDetails(
                                TransferDetails
                                    .builder()
                                    .toAccountId(toAccount.getId())
                                    .fromAccountId(fromAccount.getId())
                                    .build())
                            .amount(request.getAmount())
                            .transactionDate(LocalDateTime.now())
                            .type(TransactionType.TRANSFER.getCode())
                            .ownerId(fromAccount.getOwnerId())
                            .build()
                    ))
                    .map(accountTransactionResponseMapper::toTransferDto);
              });
        });
  }

  private Mono<Account> updateAccountBalance(Account account, Double amount,
      TransactionType transactionType) {
    if (transactionType == TransactionType.WITHDRAWAL && account.getBalance() < amount) {
      return Mono.error(
          new AccountTransactionException(HttpStatus.BAD_REQUEST,
              TransactionConstant.INSUFFICIENT_FUNDS));
    }

    Double newBalance = transactionType == TransactionType.DEPOSIT
        ? account.getBalance() + amount
        : account.getBalance() - amount;

    account.setBalance(newBalance);
    return Mono.just(account);
  }


  private Mono<Transaction> createTransaction(Account account, TransactionType transactionType,
      Double amount) {
    return accountTransactionPersistencePort.create(
        Transaction.builder()
            .transactionDate(LocalDateTime.now())
            .accountId(account.getId())
            .amount(amount)
            .type(transactionType.getCode())
            .ownerId(account.getOwnerId())
            .build());
  }

  private void handleCommission(Account account) {
    if (account.getMaxMonthlyTransactions() >= TransactionConstant.MAX_FREE_TRANSACTIONS) {
      if (account.getBalance() < TransactionConstant.COMMISSION_FEE) {
        throw new InsufficientFundsException(TransactionConstant.INSUFFICIENT_FUNDS_FOR_COMMISSION);
      }
      account.setBalance(account.getBalance() - TransactionConstant.COMMISSION_FEE);
    } else {
      account.setMaxMonthlyTransactions(account.getMaxMonthlyTransactions() + 1);
    }
  }
}