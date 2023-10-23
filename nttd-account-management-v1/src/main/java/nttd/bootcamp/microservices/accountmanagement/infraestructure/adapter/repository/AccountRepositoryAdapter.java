package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import nttd.bootcamp.microservices.accountmanagement.domain.model.constants.AccountConstant;
import nttd.bootcamp.microservices.accountmanagement.domain.port.AccountPersistencePort;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.entities.AccountEntity;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.exception.AccountException;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.mapper.AccountDboMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class AccountRepositoryAdapter implements AccountPersistencePort {

  private final AccountRepository accountRepository;
  private final AccountDboMapper accountDboMapper;

  @Override
  public Mono<Account> create(Account model) {
    AccountEntity accountEntity = accountDboMapper.toDbo(model);
    return accountRepository.save(accountEntity).map(accountDboMapper::toDomain);
  }

  @Override
  public Mono<Account> getById(String id) {
    return accountRepository.findById(id).map(accountDboMapper::toDomain);
  }

  @Override
  public Flux<Account> findByOwnerId(String ownerId) {
    return accountRepository.findByOwnerId(ownerId)
        .map(accountDboMapper::toDomain)
        .onErrorResume(ex -> Flux.error(
            new AccountException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex)));
  }

  @Override
  public Flux<Account> getAll() {
    return accountRepository.findAll()
        .map(accountDboMapper::toDomain)
        .onErrorResume(ex -> Flux.error(
            new AccountException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex)));
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return accountRepository.findById(id)
        .switchIfEmpty(Mono.error(
            new AccountException(HttpStatus.NOT_FOUND, AccountConstant.ACCOUNT_NOT_FOUND_MESSAGE)))
        .flatMap(account -> accountRepository.deleteById(id))
        .then()
        .onErrorResume(ex -> Mono.error(
            new AccountException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex)));
  }

  @Override
  public Mono<Account> update(Account account) {
    return accountRepository.findById(account.getId())
        .switchIfEmpty(Mono.error(
            new AccountException(HttpStatus.NOT_FOUND, AccountConstant.ACCOUNT_NOT_FOUND_MESSAGE)))
        .flatMap(existingAccount -> {
          existingAccount.setBalance(account.getBalance());
          return accountRepository.save(existingAccount);
        })
        .map(accountDboMapper::toDomain)
        .onErrorResume(ex -> {
          return Mono.error(
              new AccountException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex));
        });
  }

  @Override
  public Flux<Account> getByIds(List<String> accountIds) {
    return accountRepository.findAllById(accountIds)
        .map(accountDboMapper::toDomain)
        .onErrorResume(ex -> Flux.error(
            new AccountException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex)));
  }
}
