package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.repository;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.entity.DebitCardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DebitCardRepository extends ReactiveMongoRepository<DebitCardEntity,String> {
    Mono<DebitCardEntity> findByCardNumber(String cardNumber);
}
