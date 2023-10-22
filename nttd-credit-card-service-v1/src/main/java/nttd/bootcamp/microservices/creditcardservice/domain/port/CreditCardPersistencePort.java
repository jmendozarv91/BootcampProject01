package nttd.bootcamp.microservices.creditcardservice.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardPersistencePort {

  Mono<CreditCard> create(CreditCard creditCard);

  Mono<CreditCard> getById(String id);

  Flux<CreditCard> getAll();

  Mono<Void> deleteById(String id);

  Mono<CreditCard> update(CreditCard creditCard);

  Flux<CreditCard> getByIds(List<String> creditCardIds);

}
