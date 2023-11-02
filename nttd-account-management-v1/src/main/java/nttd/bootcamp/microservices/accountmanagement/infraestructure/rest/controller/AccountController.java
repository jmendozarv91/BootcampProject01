package nttd.bootcamp.microservices.accountmanagement.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.accountmanagement.application.services.AccountManagementService;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountBalanceRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransferRequest;
import org.openapitools.api.AccountManagementApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AccountController implements AccountManagementApi {

  private final AccountManagementService accountManagementService;

  @Override
  public Mono<ResponseEntity<AccountResponse>> getAccount(String accountId,
      ServerWebExchange exchange) {
    return accountManagementService.findById(accountId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @Override
  public Mono<ResponseEntity<AccountResponse>> modifyAccountBalance(String id,
      Mono<AccountBalanceRequest> accountBalanceRequest, ServerWebExchange exchange) {
    return accountBalanceRequest
        .flatMap(request -> accountManagementService.updateBalanceAccount(id, request))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<AccountResponse>> saveAccountBusiness(
      Mono<AccountRequest> accountRequest, ServerWebExchange exchange) {
    return accountRequest
        .flatMap(accountManagementService::saveAccountBusiness)
        .map(ResponseEntity::ok)
        .onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().build()));
  }

  @Override
  public Mono<ResponseEntity<AccountResponse>> saveAccountPersonal(
      Mono<AccountRequest> accountRequest, ServerWebExchange exchange) {
    return accountRequest
        .flatMap(accountManagementService::saveAccountPersonal)
        .map(ResponseEntity::ok)
        .onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().build()));
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> makeTransfer(
      Mono<TransferRequest> transferRequest, ServerWebExchange exchange) {
    return transferRequest
        .flatMap(accountManagementService::transferAccount)
        .map(ResponseEntity::ok)
        .onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().build()));
  }
}
