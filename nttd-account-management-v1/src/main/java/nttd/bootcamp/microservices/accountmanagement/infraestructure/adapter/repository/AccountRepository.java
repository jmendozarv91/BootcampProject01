package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.repository;

import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.entities.AccountEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<AccountEntity, String> {

  Flux<AccountEntity> findByOwnerId(String ownerId);
}
