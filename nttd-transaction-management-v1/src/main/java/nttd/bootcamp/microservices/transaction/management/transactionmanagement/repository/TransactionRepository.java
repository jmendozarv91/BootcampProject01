package nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository;

import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends ReactiveMongoRepository<TransactionEntity, String> {

}
