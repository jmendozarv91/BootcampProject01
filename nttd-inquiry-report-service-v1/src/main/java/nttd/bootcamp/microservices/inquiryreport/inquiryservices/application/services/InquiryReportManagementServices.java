package nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.mapper.InquiryReportRequestMapper;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.mapper.InquiryReportResponseMapper;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.usercases.InquiryReportService;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AverageBalanceSummaryResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardBalanceResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.TransactionWrapper;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.TransactionsResponse;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.AccountTxServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.CreditCardTxServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.CreditTxServicePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@AllArgsConstructor
public class InquiryReportManagementServices implements InquiryReportService {

  private final CreditTxServicePort creditTxServicePort;
  private final AccountTxServicePort accountTxServicePort;
  private final CreditCardTxServicePort creditCardTxServicePort;
  private final InquiryReportRequestMapper inquiryReportRequestMapper;
  private final InquiryReportResponseMapper inquiryReportResponseMapper;

  @Override
  public Mono<AverageBalanceSummaryResponse> generateAverageBalanceSummary(String clientId) {
    return null;
  }

  @Override
  public Mono<AccountBalanceResponse> getAccountBalance(String accountId) {
    return null;
  }

  @Override
  public Mono<CreditCardBalanceResponse> getCreditCardBalance(String cardId) {
    return null;
  }

  @Override
  public Mono<TransactionsResponse> getTransaction(String clientId) {
    Mono<List<AccountTransaction>> accountTransactionsMono = accountTxServicePort
        .getAccountTransactions(clientId)
        .collectList()
        .onErrorResume(e -> {
          log.error("Error fetching account transactions for clientId: {}", clientId, e);
          return Mono.just(Collections.emptyList());
        });

    Mono<List<CreditCardTransaction>> creditCardTransactionsMono = creditCardTxServicePort
        .getCreditCardTransactions(clientId)
        .collectList()
        .onErrorResume(e -> {
          log.error("Err fetching credit card transactions for clientId: {}", clientId, e);
          return Mono.just(Collections.emptyList());
        });

    Mono<List<CreditTransaction>> creditTransactionsMono = creditTxServicePort
        .getCreditCardTransactions(clientId)
        .collectList()
        .onErrorResume(e -> {
          log.error("Error fetching credit transactions for clientId: {}", clientId, e);
          return Mono.just(Collections.emptyList());
        });

    return Mono.zip(accountTransactionsMono, creditCardTransactionsMono, creditTransactionsMono)
        .map(tuple -> {
          TransactionWrapper transactionWrapper = new TransactionWrapper();
          transactionWrapper.setAccount(tuple.getT1());
          transactionWrapper.setCreditcard(tuple.getT2());
          transactionWrapper.setCredit(tuple.getT3());

          TransactionsResponse transactionsResponse = new TransactionsResponse();
          transactionsResponse.setClientId(clientId);
          transactionsResponse.setTransactions(Collections.singletonList(transactionWrapper));

          return transactionsResponse;
        })
        .doOnSuccess(
            response -> log.info("Transactions fetched successfully for clientId: {}", clientId))
        .doOnError(
            error -> log.error("Error fetching transactions for clientId: {}", clientId, error));
  }


}
