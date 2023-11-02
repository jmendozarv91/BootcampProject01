package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditCardTx;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditCardTxPersistencePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.mapper.CreditCardTxDboMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreditCardTxRepositoryAdapter implements CreditCardTxPersistencePort {

  private final CreditCardTxRepository creditCardTxRepository;

  private final CreditCardTxDboMapper creditCardTxDboMapper;

  @Override
  public Mono<CreditCardTx> create(CreditCardTx model) {
    TransactionEntity transactionEntity = creditCardTxDboMapper.toDbo(model);
    return creditCardTxRepository.save(transactionEntity).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Mono<CreditCardTx> getById(String id) {
    return creditCardTxRepository.findById(id).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Flux<CreditCardTx> getAll() {
    return creditCardTxRepository.findAll().map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<CreditCardTx> update(CreditCardTx model) {
    TransactionEntity transactionEntity = creditCardTxDboMapper.toDbo(model);
    return creditCardTxRepository.save(transactionEntity).map(creditCardTxDboMapper::toDomain);
  }

  @Override
  public Flux<CreditCardTx> getByIds(List<String> creditCardIds) {
    return null;
  }
}
