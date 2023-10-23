package nttd.bootcamp.microservices.accountmanagement.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountPersistencePort {

  Mono<Account> create(Account creditCard);

  Mono<Account> getById(String id);

  Flux<Account> findByOwnerId(String ownerId);

  Flux<Account> getAll();

  Mono<Void> deleteById(String id);

  Mono<Account> update(Account creditCard);

  Flux<Account> getByIds(List<String> accountIds);
}
