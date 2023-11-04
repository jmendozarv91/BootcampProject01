package nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.external;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.Transaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.AccountTxServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.config.properties.AccountTxProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountTxServiceAdapter implements AccountTxServicePort {

  private final WebClient webClient;

  public AccountTxServiceAdapter(WebClient.Builder webClientBuilder,
      AccountTxProperties accountTxProperties) {
    this.webClient = webClientBuilder.baseUrl(accountTxProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }
  @Override
  public Flux<AccountTransaction> getAccountTransactions(String clientId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/{clientId}/transactions").build(clientId))
        .retrieve()
        .bodyToFlux(AccountTransaction.class);
  }
}
