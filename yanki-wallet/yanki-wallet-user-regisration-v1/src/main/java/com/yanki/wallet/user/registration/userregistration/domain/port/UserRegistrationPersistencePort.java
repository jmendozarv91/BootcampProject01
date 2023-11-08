package com.yanki.wallet.user.registration.userregistration.domain.port;

import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRegistrationPersistencePort {

  Mono<User> create(User model);

  Mono<User> getById(String id);

  Flux<User> getAll();

  Mono<Void> deleteById(String id);

  Mono<User> update(String id , User model);

  Flux<User> getByIds(List<String> ids);

}
