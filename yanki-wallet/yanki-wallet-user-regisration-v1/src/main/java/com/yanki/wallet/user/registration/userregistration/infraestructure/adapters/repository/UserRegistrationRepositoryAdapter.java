package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.repository;

import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import com.yanki.wallet.user.registration.userregistration.domain.port.UserRegistrationPersistencePort;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.entity.UserEntity;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.mapper.UserRegistrationDboMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class UserRegistrationRepositoryAdapter implements UserRegistrationPersistencePort {

  private final UserRegistrationRepository userRegistrationRepository;
  private final UserRegistrationDboMapper userRegistrationDboMapper;

  @Override
  public Mono<User> create(User request) {
    UserEntity userEntity = userRegistrationDboMapper.toDbo(request);
    return userRegistrationRepository.save(userEntity)
        .map(userRegistrationDboMapper::toDomain);
  }

  @Override
  public Mono<User> getById(String id) {
    return userRegistrationRepository.findById(id)
        .map(userRegistrationDboMapper::toDomain);
  }

  @Override
  public Flux<User> getAll() {
    return userRegistrationRepository.findAll()
        .map(userRegistrationDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return userRegistrationRepository.deleteById(id);
  }

  @Override
  public Mono<User> update(String id , User model) {
    return userRegistrationRepository.findById(id)
        .flatMap(userEntity -> {
          UserEntity updatedUserDbo = userRegistrationDboMapper.updateDbo(model, userEntity);
          return userRegistrationRepository.save(updatedUserDbo);
        })
        .map(userRegistrationDboMapper::toDomain);
  }

  @Override
  public Flux<User> getByIds(List<String> ids) {
    return userRegistrationRepository.findAllById(ids)
        .map(userRegistrationDboMapper::toDomain);
  }
}
