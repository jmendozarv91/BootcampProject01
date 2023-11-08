package com.yanki.wallet.user.registration.userregistration.infraestructure.rest.controller;

import com.yanki.wallet.user.registration.userregistration.application.services.UserRegistrationManagementService;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.NewUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UpdateUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UserResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletTransaction;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.exception.UserRegistrationException;
import lombok.AllArgsConstructor;
import org.openapitools.api.UsersApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RestController
@AllArgsConstructor
public class UserRegistrationController implements UsersApi {

  private final UserRegistrationManagementService userRegistrationManagementService;

  @Override
  public Mono<ResponseEntity<UserResponse>> createUser(Mono<NewUserRequest> newUserRequest,
      ServerWebExchange exchange) {
    return newUserRequest
        .flatMap(userRegistrationManagementService::createUser)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteUser(String userId, ServerWebExchange exchange) {
    return userRegistrationManagementService.deleteUser(userId)
        .map(r -> ResponseEntity.noContent().<Void>build())
        .onErrorResume(e -> Mono.error(new ResponseStatusException(
            HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<ResponseEntity<UserResponse>> getUserById(String userId, ServerWebExchange exchange) {
    return userRegistrationManagementService.getUserById(userId)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<ResponseEntity<WalletResponse>> getWalletByUserId(String userId,
      ServerWebExchange exchange) {
    return userRegistrationManagementService.getWalletByUserId(userId)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<ResponseEntity<Void>> updateUser(String userId,
      Mono<UpdateUserRequest> updateUserRequest, ServerWebExchange exchange) {
    return updateUserRequest
        .flatMap(req -> userRegistrationManagementService.updateUser(userId, req))
        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        .onErrorResume(e -> {
          if (e instanceof UserRegistrationException) {
            UserRegistrationException ure = (UserRegistrationException) e;
            return Mono.just(new ResponseEntity<>(ure.getErrorCode()));
          } else {
            return Mono.error(e);
          }
        });
  }

  @Override
  public Mono<ResponseEntity<Void>> walletTransaction(String userId,
      Mono<WalletTransaction> walletTransaction, ServerWebExchange exchange) {
    return walletTransaction
        .flatMap(tx -> userRegistrationManagementService.walletTransaction(userId, tx))
        .then(Mono.just(ResponseEntity.ok().<Void>build()))
        .onErrorResume(e -> {
          if (e instanceof UserRegistrationException) {
            UserRegistrationException ure = (UserRegistrationException) e;
            return Mono.just(ResponseEntity.status(ure.getErrorCode()).build());
          } else {
            return Mono.just(ResponseEntity.notFound().build());
          }
        });
  }
}
