package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.repository;

import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditTxRepository extends ReactiveMongoRepository<TransactionEntity,String> {

}
