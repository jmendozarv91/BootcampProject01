package nttd.bootcamp.microservices.creditcardservice.application.usecases;

import nttd.bootcamp.microservices.creditcardservice.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {

  Mono<Customer> getClientById(String id);
}
