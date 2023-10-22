package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.repository;

import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.entities.CreditCardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCardEntity,String> {


}
