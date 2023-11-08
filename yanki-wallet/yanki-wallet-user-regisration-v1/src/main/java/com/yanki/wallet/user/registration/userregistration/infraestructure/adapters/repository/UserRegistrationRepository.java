package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.repository;

import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends ReactiveMongoRepository<UserEntity,String> {

}
