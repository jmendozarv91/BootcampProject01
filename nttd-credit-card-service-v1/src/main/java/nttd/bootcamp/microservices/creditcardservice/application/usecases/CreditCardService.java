package nttd.bootcamp.microservices.creditcardservice.application.usecases;

import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardDto;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.CreditCardRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardService {

  Mono<CreditCardDto> createNew(CreditCardRequest creditCardRequest);

  Mono<CreditCardDto> getById(String id);

  Flux<CreditCardDto> getAll();

  Mono<Void> deleteById(String id);

  Mono<CreditCardDto> update(CreditCardRequest request, String id);

  Mono<Void> executeConsumption(
      String clientId,
      String creditCardId, ConsumptionRequest request);

}
