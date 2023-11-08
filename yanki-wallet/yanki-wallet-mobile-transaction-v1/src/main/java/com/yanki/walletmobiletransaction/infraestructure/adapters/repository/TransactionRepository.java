package com.yanki.walletmobiletransaction.infraestructure.adapters.repository;

import com.yanki.walletmobiletransaction.infraestructure.adapters.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, String> {

}
