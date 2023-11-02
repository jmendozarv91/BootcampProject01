package nttd.bootcamp.microservices.credittransaction.domain.port;

import java.util.List;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditTx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditTxPersistencePort {

  Mono<CreditTx> create(CreditTx model);

  Mono<CreditTx> getById(String id);

  Flux<CreditTx> getAll();

  Mono<Void> deleteById(String id);

  Mono<CreditTx> update(CreditTx model);

  Flux<CreditTx> getByIds(List<String> creditCardIds);

}
