package nttd.bootcamp.microservices.credittransaction.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.application.services.CreditCardTxManagementService;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionResponse;
import org.openapitools.api.CreditCardApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class CreditCardTxController implements CreditCardApi {

  private final CreditCardTxManagementService creditCardTxManagementService;

  @Override
  public Mono<ResponseEntity<ConsumptionResponse>> processConsumption(String clientId,
      String creditCardId, Mono<ConsumptionRequest> consumptionRequest,
      ServerWebExchange exchange) {
    return consumptionRequest.flatMap(request ->
        creditCardTxManagementService.processConsumption(clientId, creditCardId, request)
            .map(ResponseEntity::ok)
    );
  }
}
