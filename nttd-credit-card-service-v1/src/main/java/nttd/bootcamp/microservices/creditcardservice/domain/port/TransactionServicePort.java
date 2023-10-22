package nttd.bootcamp.microservices.creditcardservice.domain.port;

import nttd.bootcamp.microservices.creditcardservice.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionServicePort {
    Mono<Transaction> createTransaction(Transaction transaction);
}
