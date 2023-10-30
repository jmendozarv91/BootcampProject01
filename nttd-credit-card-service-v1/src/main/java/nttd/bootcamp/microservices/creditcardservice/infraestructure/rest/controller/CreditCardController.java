package nttd.bootcamp.microservices.creditcardservice.infraestructure.rest.controller;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.application.services.CreditCardManagementService;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.AverageDailyBalanceResponse;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardResponse;
import org.openapitools.api.CreditCardApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class CreditCardController implements CreditCardApi {

  private final CreditCardManagementService creditCardService;

  @Override
  public Mono<ResponseEntity<Void>> processConsumption(String clientId, String creditCardId,
      Mono<ConsumptionRequest> consumptionRequest, ServerWebExchange exchange) {
    return consumptionRequest
        .flatMap(request -> creditCardService.executeConsumption(clientId, creditCardId, request))
        .thenReturn(ResponseEntity.ok().build());
  }

  @Override
  public Mono<ResponseEntity<CreditCardResponse>> saveTransaction(
      Mono<CreditCardRequest> creditCardRequest, ServerWebExchange exchange) {
    return creditCardRequest
        .flatMap(creditCardService::createNew)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build())
        .onErrorResume(
            ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
  }

  @Override
  public Mono<ResponseEntity<Boolean>> creditCardHasCreditCardClientIdGet(String clientId,
      ServerWebExchange exchange) {
    return creditCardService.hasCreditCard(clientId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build())
        .onErrorMap(ex -> new RuntimeException("Internal Server Error"));
  }

  @Override
  public Mono<ResponseEntity<AverageDailyBalanceResponse>> creditCardAverageDailyBalanceGet(
      String clientId, LocalDate startDate, LocalDate endDate, ServerWebExchange exchange) {
    return null;
//    return creditCardService.getAverageDailyBalance(clientId, startDate, endDate)
//        .map(ResponseEntity::ok)
//        .defaultIfEmpty(ResponseEntity.notFound().build())
//        .onErrorMap(ex -> new RuntimeException("Internal Server Error"));
  }
}
