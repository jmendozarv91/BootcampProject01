package nttd.bootcamp.microservices.debitcardmanagement.application.usecases;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {

  Mono<CreateDebitCardResponse> createDebitCard(CreateDebitCardRequest debitCardRequest);

  Flux<CreateDebitCardResponse> getAllDebitCards();

  Mono<Void> associateDebitCardWithAccount(String cardNumber, String accountNumber);

  Mono<PerformTransactionResponse> performTransaction(String cardNumber,
      PerformTransactionRequest transactionRequest);

}
