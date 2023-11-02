package nttd.bootcamp.microservices.credittransaction.application.usecases;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditPaymentRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.TransactionResponse;
import reactor.core.publisher.Mono;

public interface CreditTxService {

  Mono<TransactionResponse> paymentCredit(String creditId,  CreditPaymentRequest request);
}
