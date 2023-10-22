package nttd.bootcamp.microservices.creditcardservice.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.application.services.CreditCardManagementService;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardResponse;
import org.openapitools.api.CreditCardApi;
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
        .then(Mono.just(ResponseEntity.ok().build()));
  }

  @Override
  public Mono<ResponseEntity<CreditCardResponse>> saveTransaction(
      Mono<CreditCardRequest> creditCardRequest, ServerWebExchange exchange) {
    return null;
  }
}
