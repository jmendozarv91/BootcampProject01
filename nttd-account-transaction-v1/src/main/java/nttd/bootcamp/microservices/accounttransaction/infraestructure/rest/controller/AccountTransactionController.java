package nttd.bootcamp.microservices.accounttransaction.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.accounttransaction.application.services.AccountTransactionManagementService;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferResponse;
import org.openapitools.api.AccountsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AccountTransactionController implements AccountsApi {

  private final AccountTransactionManagementService accountTransactionManagementService;

  @Override
  public Mono<ResponseEntity<TransactionResponse>> depositTransaction(String accountId,
      Mono<TransactionRequest> transactionRequest, ServerWebExchange exchange) {
    return transactionRequest
        .flatMap(
            request -> accountTransactionManagementService.depositTransaction(accountId,
                request))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> withdrawalTransaction(String accountId,
      Mono<TransactionRequest> transactionRequest, ServerWebExchange exchange) {
    return transactionRequest
        .flatMap(request -> accountTransactionManagementService.withdrawalTransaction(accountId,
            request))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @Override
  public Mono<ResponseEntity<TransferResponse>> makeTransfer(
      Mono<TransferRequest> transferRequest, ServerWebExchange exchange) {
    return transferRequest
        .flatMap(accountTransactionManagementService::makeTransfer)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<TransactionResponse>>> getAccountTransactions(String ownerId,
      String transactionType, ServerWebExchange exchange) {
    Flux<TransactionResponse> transactionFlux = accountTransactionManagementService.getAccountTransactions(
        ownerId);
    return transactionFlux.hasElements()
        .map(hasElements -> hasElements ?
            ResponseEntity.ok().body(transactionFlux) :
            ResponseEntity.notFound().build()
        );
  }

}