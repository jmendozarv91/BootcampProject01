package nttd.bootcamp.microservices.accounttransaction.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.accounttransaction.domain.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountTransactionPersistencePort {

  Mono<Transaction> create(Transaction transaction);

  Mono<Transaction> getById(String id);

  Flux<Transaction> getAll();

  Mono<Void> deleteById(String id);

  Mono<Transaction> update(Transaction transaction);

  Flux<Transaction> getByIds(List<String> accountIds);
}
