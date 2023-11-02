package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditTx;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditTxPersistencePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.mapper.CreditTxDboMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreditTxRepositoryAdapter implements CreditTxPersistencePort {

  private final CreditTxRepository creditCardTxRepository;

  private final CreditTxDboMapper creditCardTxDboMapper;

  @Override
  public Mono<CreditTx> create(CreditTx model) {
    TransactionEntity transactionEntity = creditCardTxDboMapper.toDbo(model);
    return creditCardTxRepository.save(transactionEntity).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Mono<CreditTx> getById(String id) {
    return creditCardTxRepository.findById(id).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Flux<CreditTx> getAll() {
    return creditCardTxRepository.findAll().map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<CreditTx> update(CreditTx model) {
    TransactionEntity transactionEntity = creditCardTxDboMapper.toDbo(model);
    return creditCardTxRepository.save(transactionEntity).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Flux<CreditTx> getByIds(List<String> creditCardIds) {
    return null;
  }
}
