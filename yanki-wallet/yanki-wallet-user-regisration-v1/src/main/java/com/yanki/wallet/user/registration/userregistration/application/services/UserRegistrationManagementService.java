package com.yanki.wallet.user.registration.userregistration.application.services;

import com.yanki.wallet.user.registration.userregistration.application.mapper.UserRegistrationRequestMapper;
import com.yanki.wallet.user.registration.userregistration.application.mapper.UserRegistrationResponseMapper;
import com.yanki.wallet.user.registration.userregistration.application.usecases.UserRegistrationService;
import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import com.yanki.wallet.user.registration.userregistration.domain.model.User.PersonalInfo;
import com.yanki.wallet.user.registration.userregistration.domain.model.User.Wallet;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.NewUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UpdateUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UserResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletTransaction;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletTransaction.TypeEnum;
import com.yanki.wallet.user.registration.userregistration.domain.port.UserRegistrationPersistencePort;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.exception.UserRegistrationException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserRegistrationManagementService implements UserRegistrationService {

  private final UserRegistrationPersistencePort userRegistrationPersistencePort;
  private final UserRegistrationRequestMapper userRegistrationRequestMapper;
  private final UserRegistrationResponseMapper userRegistrationResponseMapper;

  @Override
  public Mono<UserResponse> createUser(NewUserRequest newUserRequest) {
    return Mono.just(newUserRequest)
        .map(userRegistrationRequestMapper::toDomain)
        .flatMap(userRegistrationPersistencePort::create)
        .map(userRegistrationResponseMapper::toUserResponse);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteUser(String userId) {
    return userRegistrationPersistencePort.deleteById(userId)
        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
        .onErrorResume(e -> Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<UserResponse> getUserById(String userId) {
    return userRegistrationPersistencePort.getById(userId)
        .map(userRegistrationResponseMapper::toUserResponse)
        .cast(UserResponse.class)
        .switchIfEmpty(
            Mono.error(new UserRegistrationException(HttpStatus.NOT_FOUND, "User not found")));
  }

  @Override
  public Mono<Void> updateUser(String userId, UpdateUserRequest updateUserRequest) {
    return userRegistrationPersistencePort.getById(userId)
        .switchIfEmpty(
            Mono.error(new UserRegistrationException(HttpStatus.NOT_FOUND, "User not found")))
        .flatMap(user -> {
          // Map the UpdateUserRequest to the domain object and apply changes
          User updatedUser = userRegistrationRequestMapper.toDomainUpdateUser(updateUserRequest);
          updatedUser.setId(user.getId()); // Ensure the ID remains unchanged
          updatedUser.setPersonalInfo(PersonalInfo
              .builder()
              .email(updateUserRequest.getEmail())
              .build());
          return userRegistrationPersistencePort.update(userId, updatedUser);
        })
        .then();
  }

  @Override
  public Mono<Void> walletTransaction(String userId, WalletTransaction walletTransaction) {
    return userRegistrationPersistencePort.getById(userId)
        .switchIfEmpty(
            Mono.error(new UserRegistrationException(HttpStatus.NOT_FOUND, "User not found")))
        .flatMap(user -> {

          if (user.getWallet() == null) {
            user.setWallet(Wallet.builder().id(UUID.randomUUID().toString()).balance(0.0).build());
          }

          if (TypeEnum.CREDIT.equals(walletTransaction.getType())) {
            user.getWallet().setBalance(
                user.getWallet().getBalance() + walletTransaction.getAmount().doubleValue());
          } else if (TypeEnum.DEBIT.equals(walletTransaction.getType())) {
            double newBalance =
                user.getWallet().getBalance() - walletTransaction.getAmount().doubleValue();
            if (newBalance < 0) {
              return Mono.error(
                  new UserRegistrationException(HttpStatus.BAD_REQUEST, "Insufficient funds"));
            }
            user.getWallet().setBalance(newBalance);
          }
          return userRegistrationPersistencePort.update(userId, user);
        })
        .then();
  }

  @Override
  public Mono<WalletResponse> getWalletByUserId(String userId) {
    // Buscamos al usuario por su ID
    return userRegistrationPersistencePort.getById(userId)
        .map(userRegistrationResponseMapper::toWallerResponse)
        .switchIfEmpty(Mono.error(new UserRegistrationException(HttpStatus.NOT_FOUND,
            "User not found"))); // En caso de que no se encuentre el usuario
  }
}
