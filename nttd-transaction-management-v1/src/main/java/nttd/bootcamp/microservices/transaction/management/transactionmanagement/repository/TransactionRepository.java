package nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository;

import java.time.LocalDateTime;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, String> {

  Flux<TransactionEntity> findByCreditCardIdAndTransactionDateBetween(String creditCardId,
      LocalDateTime startDate, LocalDateTime endDate);

}
