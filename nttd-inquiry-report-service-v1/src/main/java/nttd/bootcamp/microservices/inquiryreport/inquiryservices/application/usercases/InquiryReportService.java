package nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.usercases;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.TransactionsResponse;
import reactor.core.publisher.Mono;

public interface InquiryReportService {
  Mono<AverageBalanceSummaryResponse> generateAverageBalanceSummary(String clientId);

  Mono<AccountBalanceResponse> getAccountBalance(String accountId);

  Mono<CreditCardBalanceResponse> getCreditCardBalance(String cardId);

  Mono<TransactionsResponse> getTransaction(String clientId);


}
