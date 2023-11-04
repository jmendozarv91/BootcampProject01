package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.repository;

import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountTransactionRepository extends
    ReactiveMongoRepository<TransactionEntity, String> {

  Flux<TransactionEntity> findAllByOwnerId(String ownerId);
}
