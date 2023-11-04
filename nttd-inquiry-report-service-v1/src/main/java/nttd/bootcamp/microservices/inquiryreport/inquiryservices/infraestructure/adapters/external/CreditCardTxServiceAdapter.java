package nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.external;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.Transaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.CreditCardTxServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.config.properties.AccountTxProperties;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.config.properties.CreditCardTxProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CreditCardTxServiceAdapter implements CreditCardTxServicePort {

  private final WebClient webClient;

  public CreditCardTxServiceAdapter(WebClient.Builder webClientBuilder,
      CreditCardTxProperties creditCardTxProperties) {
    this.webClient = webClientBuilder.baseUrl(creditCardTxProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }
  @Override
  public Flux<CreditCardTransaction> getCreditCardTransactions(String clientId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/{clientId}/transactions").build(clientId))
        .retrieve()
        .bodyToFlux(CreditCardTransaction.class);
  }
}
