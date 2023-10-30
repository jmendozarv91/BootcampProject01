package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.debitcardmanagement.application.services.DebitCardManagementService;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.DebitCardResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.UpdateDebitCardRequest;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception.NotFoundException;
import org.openapitools.api.DebitCardsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class DebitCardController implements DebitCardsApi {

  private final DebitCardManagementService debitCardManagementService;

  @Override
  public Mono<ResponseEntity<Void>> associateDebitCardWithAccount(String cardNumber,
      String accountNumber, ServerWebExchange exchange) {
      return null;
  }

  @Override
  public Mono<ResponseEntity<CreateDebitCardResponse>> createDebitCard(
      Mono<CreateDebitCardRequest> createDebitCardRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<PerformTransactionResponse>> performTransaction(String cardNumber,
      Mono<PerformTransactionRequest> performTransactionRequest, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Flux<DebitCardResponse>>> getAllDebitCards(
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<DebitCardResponse>> updateDebitCard(String cardNumber,
      Mono<UpdateDebitCardRequest> updateDebitCardRequest, ServerWebExchange exchange) {
    return null;
  }
}
