package nttd.bootcamp.microservices.credittransaction.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditCardTx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardTxPersistencePort {

  Mono<CreditCardTx> create(CreditCardTx model);

  Mono<CreditCardTx> getById(String id);

  Flux<CreditCardTx> getAll();

  Flux<CreditCardTx> getTransactionsById(String id);
  Flux<CreditCardTx> getTransactionsByClientId(String clientId);

  Mono<Void> deleteById(String id);

  Mono<CreditCardTx> update(CreditCardTx model);

  Flux<CreditCardTx> getByIds(List<String> creditCardIds);

}
