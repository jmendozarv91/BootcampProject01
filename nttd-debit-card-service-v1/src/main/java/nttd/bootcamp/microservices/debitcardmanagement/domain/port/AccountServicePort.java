package nttd.bootcamp.microservices.debitcardmanagement.domain.port;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.Account;
import reactor.core.publisher.Mono;

public interface AccountServicePort {
  Mono<Account> findById(String id);

//  Mono<?> updateAccountBalance(String id, double v);
}
