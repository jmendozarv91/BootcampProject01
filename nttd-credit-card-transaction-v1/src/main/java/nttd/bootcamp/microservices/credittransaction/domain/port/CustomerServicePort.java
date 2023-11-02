package nttd.bootcamp.microservices.credittransaction.domain.port;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.Customer;
import reactor.core.publisher.Mono;

public interface CustomerServicePort {
  Mono<Customer> getClientById(String id);
}
