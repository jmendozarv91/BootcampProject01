package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.repository;

import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CreditCardTxRepository extends ReactiveMongoRepository<TransactionEntity, String> {

  Flux<TransactionEntity> findAllById(String id);

  Flux<TransactionEntity> findAllByClientId(String clientId);
}
