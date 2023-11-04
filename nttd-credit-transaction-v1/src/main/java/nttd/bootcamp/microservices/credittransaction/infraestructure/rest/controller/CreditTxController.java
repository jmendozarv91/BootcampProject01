package nttd.bootcamp.microservices.credittransaction.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.application.services.CreditTxManagementService;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditPaymentRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditTransactionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.TransactionResponse;
import org.openapitools.api.CreditsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class CreditTxController implements CreditsApi {

  private final CreditTxManagementService creditCardTxManagementService;

  @Override
  public Mono<ResponseEntity<Flux<CreditTransactionResponse>>> getCreditTransactions(
      String clientId, ServerWebExchange exchange) {
    Flux<CreditTransactionResponse> transactionFlux = creditCardTxManagementService.getCreditTransactions(
        clientId);
    return transactionFlux.hasElements()
        .map(hasElements -> hasElements ?
            ResponseEntity.ok().body(transactionFlux) :
            ResponseEntity.notFound().build()
        );
  }

  @Override
  public Mono<ResponseEntity<TransactionResponse>> makeCreditPayment(String creditId,
      Mono<CreditPaymentRequest> creditPaymentRequest, ServerWebExchange exchange) {
    return creditPaymentRequest
        .flatMap(
            request -> creditCardTxManagementService.paymentCredit(creditId, request))
        .thenReturn(ResponseEntity.ok().build());
  }
}
