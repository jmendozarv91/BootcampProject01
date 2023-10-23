package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {
  Mono<Transaction> saveTransaction(Transaction transaction);
}
