package nttd.bootcamp.microservices.credittransaction.application.usecases;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditCardTransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardTxService {

  Mono<ConsumptionResponse> processConsumption(String clientId,
      String creditCardId, ConsumptionRequest consumptionRequest);

  Flux<CreditCardTransactionResponse> getCreditCardTransactions(String creditCardId);

}
