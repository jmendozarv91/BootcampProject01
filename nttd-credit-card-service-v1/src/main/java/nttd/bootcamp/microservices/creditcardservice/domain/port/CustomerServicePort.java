package nttd.bootcamp.microservices.creditcardservice.domain.port;

import nttd.bootcamp.microservices.creditcardservice.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerServicePort {

 Mono<Customer> getClientById(String id);

}
