package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountBalanceRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransferRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

  Flux<AccountResponse> findAllAccounts();

  Mono<AccountResponse> findById(String id);
  Flux<AccountResponse> findByOwnerId(String ownerId);

  Mono<Void> deleteById(String id);

  Mono<AccountResponse> saveAccountPersonal(AccountRequest request);

  Mono<AccountResponse> saveAccountBusiness(AccountRequest request);

  Mono<AccountResponse> updateBalanceAccount(String id, AccountBalanceRequest request);

  Mono<TransactionResponse> transferAccount(TransferRequest request);

}
