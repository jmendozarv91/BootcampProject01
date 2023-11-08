package com.yanki.wallet.user.registration.userregistration.application.usecases;

import com.yanki.wallet.user.registration.userregistration.domain.model.dto.NewUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UpdateUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UserResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

  public Mono<UserResponse> createUser(NewUserRequest newUserRequest);

  public Mono<ResponseEntity<Void>> deleteUser(String userId);

  public Mono<UserResponse> getUserById(String userId);

  public Mono<WalletResponse> getWalletByUserId(String userId);

  public Mono<Void> updateUser(String userId,
      UpdateUserRequest updateUserRequest);

  public Mono<Void> walletTransaction(String userId,
      WalletTransaction walletTransaction);


}
