package nttd.bootcamp.microservices.accountmanagement.domain.port;

import reactor.core.publisher.Mono;

public interface CreditCardServicePort {
  Mono<Boolean> hasCreditCard(String clientId);
}
