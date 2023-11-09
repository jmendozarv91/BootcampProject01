package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.repository;

import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRegistrationRepository extends ReactiveMongoRepository<UserEntity,String> {
  Mono<UserEntity> findByPersonalInfoPhoneNumber(String phoneNumber);
  Mono<UserEntity> findByWalletId(String walletId);
}
