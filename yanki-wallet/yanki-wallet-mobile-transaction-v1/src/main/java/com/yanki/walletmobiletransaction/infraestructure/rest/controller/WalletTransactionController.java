package com.yanki.walletmobiletransaction.infraestructure.rest.controller;

import com.yanki.walletmobiletransaction.application.services.WalletTransactionManagementService;
import com.yanki.walletmobiletransaction.domain.model.dto.CreditWalletRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.DebitCardLinkResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.LinkDebitCardRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.LoadWalletRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionStatusUpdateRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletBalanceResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import lombok.AllArgsConstructor;
import org.openapitools.api.TransactionsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class WalletTransactionController implements TransactionsApi {

  private final WalletTransactionManagementService walletTransactionManagementService;

  @Override
  public Mono<ResponseEntity<WalletBalanceResponse>> loadWalletFromDebitCard(String walletId,
      Mono<LoadWalletRequest> loadWalletRequest, ServerWebExchange exchange) {
    return null;
  }


  @Override
  public Mono<ResponseEntity<WalletBalanceResponse>> creditWalletToBankAccount(String walletId,
      Mono<CreditWalletRequest> creditWalletRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> transferWalletToWallet(
      Mono<WalletTransferRequest> walletTransferRequest, ServerWebExchange exchange) {
    return walletTransferRequest.flatMap(request ->
            walletTransactionManagementService.transferWalletToWallet(request)
                .map(ResponseEntity::ok))
        .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> cancelTransaction(String transactionId,
      ServerWebExchange exchange) {
    return walletTransactionManagementService.cancelTransaction(transactionId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> createTransaction(
      Mono<TransactionRequest> transactionRequest, ServerWebExchange exchange) {
    return transactionRequest.flatMap(request ->
            walletTransactionManagementService.createTransaction(request)
                .map(ResponseEntity::ok))
        .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
  }



  @Override
  public Mono<ResponseEntity<TransactionResponse>> getTransactionById(String transactionId,
      ServerWebExchange exchange) {
    return walletTransactionManagementService.getTransactionById(transactionId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<TransactionResponse>>> getTransactionsByUserId(String userId,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<WalletBalanceResponse>> getWalletBalance(String walletId,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<DebitCardLinkResponse>> linkDebitCard(
      Mono<LinkDebitCardRequest> linkDebitCardRequest, ServerWebExchange exchange) {
    return linkDebitCardRequest.flatMap(request ->
            walletTransactionManagementService.linkDebitCard(request)
                .map(ResponseEntity::ok))
        .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
  }

  @Override
  public Mono<ResponseEntity<Flux<TransactionResponse>>> listWalletTransactions(String walletId,
      ServerWebExchange exchange) {
    return null;
  }





  @Override
  public Mono<ResponseEntity<TransactionResponse>> updateTransactionStatus(String transactionId,
      Mono<TransactionStatusUpdateRequest> transactionStatusUpdateRequest,
      ServerWebExchange exchange) {
    return null;
  }
}
