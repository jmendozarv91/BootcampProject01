package nttd.bootcamp.microservices.credittransaction.application.usecases;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditPaymentRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditTransactionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.TransactionResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditTxService {

  Mono<TransactionResponse> paymentCredit(String creditId,  CreditPaymentRequest request);

  Flux<CreditTransactionResponse> getCreditTransactions(String clientId);
}
