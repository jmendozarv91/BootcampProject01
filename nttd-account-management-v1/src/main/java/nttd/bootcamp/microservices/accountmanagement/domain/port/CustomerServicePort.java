package nttd.bootcamp.microservices.accountmanagement.domain.port;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerServicePort {
  Mono<Customer> findClientById(String clientId);
}
