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
    return null;
  }

  @Override
  public Mono<ResponseEntity<AccountResponse>> modifyAccountBalance(String id,
      Mono<AccountBalanceRequest> accountBalanceRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<AccountResponse>> saveAccountBusiness(
      Mono<AccountRequest> accountRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<AccountResponse>> saveAccountPersonal(
      Mono<AccountRequest> accountRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> makeTransfer(
      Mono<TransferRequest> transferRequest, ServerWebExchange exchange) {
    return null;
  }
}
