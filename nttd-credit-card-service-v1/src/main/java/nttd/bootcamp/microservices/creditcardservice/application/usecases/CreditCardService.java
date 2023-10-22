package nttd.bootcamp.microservices.creditcardservice.application.usecases;

import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardService {

  Mono<CreditCardResponse> createNew(CreditCardRequest creditCardRequest);

  Mono<CreditCardResponse> getById(String id);

  Flux<CreditCardResponse> getAll();

  Mono<Void> deleteById(String id);

  Mono<CreditCardResponse> update(CreditCardRequest request, String id);

  Mono<Void> executeConsumption(
      String clientId,
      String creditCardId, ConsumptionRequest request);

}
