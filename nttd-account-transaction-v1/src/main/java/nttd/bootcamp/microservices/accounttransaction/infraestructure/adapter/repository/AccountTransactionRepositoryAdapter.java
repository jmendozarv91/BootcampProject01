package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.accounttransaction.domain.model.Transaction;
import nttd.bootcamp.microservices.accounttransaction.domain.port.AccountTransactionPersistencePort;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity.TransactionEntity;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.mapper.AccountTransactionDboMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Transactional
@AllArgsConstructor
public class AccountTransactionRepositoryAdapter implements AccountTransactionPersistencePort {

  private final AccountTransactionRepository accountTransactionRepository;
  private final AccountTransactionDboMapper accountTransactionDboMapper;

  @Override
  public Mono<Transaction> create(Transaction model) {
    TransactionEntity entity = accountTransactionDboMapper.toDbo(model);
    return accountTransactionRepository.save(entity).map(accountTransactionDboMapper::toDomain);
  }

  @Override
  public Mono<Transaction> getById(String id) {
    return null;
  }

  @Override
  public Flux<Transaction> getAll() {
    return null;
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<Transaction> update(Transaction transaction) {
    return null;
  }

  @Override
  public Flux<Transaction> getByIds(List<String> accountIds) {
    return null;
  }
}
