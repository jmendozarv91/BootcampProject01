package nttd.bootcamp.microservices.credittransaction.domain.port;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditCard;
import reactor.core.publisher.Mono;

public interface CreditCardServicePort {

  Mono<CreditCard> getCreditCardById(String id);

  Mono<CreditCard> updateCreditCard(String id, CreditCard request);

}
