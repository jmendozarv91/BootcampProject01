package nttd.bootcamp.microservices.debitcardmanagement.domain.port;

import reactor.core.publisher.Mono;

public interface TransactionServicePort {

  Mono<Object> performTransaction(String id, Double amount, String transactionType);
}
