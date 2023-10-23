package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
  Mono<Customer> findClientById(String clientId);
}
