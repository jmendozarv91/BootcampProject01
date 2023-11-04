package nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.services.InquiryReportManagementServices;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.TransactionsResponse;
import org.openapitools.api.ReportsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class InquiryReportController implements ReportsApi {

  private final InquiryReportManagementServices inquiryReportManagementServices;

  @Override
  public Mono<ResponseEntity<AverageBalanceSummaryResponse>> generateAverageBalanceSummary(
      String clientId, ServerWebExchange exchange) {
    return inquiryReportManagementServices.generateAverageBalanceSummary(clientId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<AccountBalanceResponse>> getAccountBalance(String accountId,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<CreditCardBalanceResponse>> getCreditCardBalance(String cardId,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<TransactionsResponse>> getTransactions(String clientId,
      ServerWebExchange exchange) {
    return inquiryReportManagementServices.getTransaction(clientId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
