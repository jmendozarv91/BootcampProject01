package nttd.bootcamp.microservices.accountmanagement.domain.port;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionServicePort {
  Mono<Transaction> saveTransaction(Transaction transaction);
}
