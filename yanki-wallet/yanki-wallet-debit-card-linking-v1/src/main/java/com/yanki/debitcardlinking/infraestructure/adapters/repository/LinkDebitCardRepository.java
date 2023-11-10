package com.yanki.debitcardlinking.infraestructure.adapters.repository;

import com.yanki.debitcardlinking.infraestructure.adapters.entity.LinkDebitCardEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LinkDebitCardRepository extends ReactiveMongoRepository<LinkDebitCardEntity,String> {

   Mono<LinkDebitCardEntity> findByCardNumberAndWalletId(String cardNumber,String walletId);
}
