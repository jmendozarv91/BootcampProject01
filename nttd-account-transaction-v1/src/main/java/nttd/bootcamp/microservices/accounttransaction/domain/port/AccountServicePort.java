package nttd.bootcamp.microservices.accounttransaction.domain.port;

import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.Account;
import reactor.core.publisher.Mono;

public interface AccountServicePort {
  Mono<Account> findAccountById(String accountId);
  Mono<Account> modifyAccountBalance(String idAccount, Account account);
}
