package nttd.bootcamp.microservices.debitcardmanagement.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardPersistencePort {

  Mono<DebitCard> create(DebitCard debitCard);
  Mono<DebitCard> findByCardNumber(String cardNumber);


  Mono<DebitCard> getById(String id);

  Flux<DebitCard> getAll();

  Mono<Void> deleteById(String id);

  Mono<DebitCard> update(DebitCard debitCard);

  Flux<DebitCard> getByIds(List<String> accountIds);

}
