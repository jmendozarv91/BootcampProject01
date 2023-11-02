package nttd.bootcamp.microservices.credittransaction.application.usecases;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionResponse;
import reactor.core.publisher.Mono;

public interface CreditCardTxService {

  Mono<ConsumptionResponse> processConsumption(String clientId,
      String creditCardId, ConsumptionRequest consumptionRequest);

}
