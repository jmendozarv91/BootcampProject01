package nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.external;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port.CreditTxServicePort;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.config.properties.CreditCardTxProperties;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.infraestructure.adapters.config.properties.CreditTxProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CreditTxServiceAdapter implements CreditTxServicePort {
  private final WebClient webClient;

  public CreditTxServiceAdapter(WebClient.Builder webClientBuilder,
      CreditTxProperties creditTxProperties) {
    this.webClient = webClientBuilder.baseUrl(creditTxProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Flux<CreditTransaction> getCreditCardTransactions(String clientId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/{clientId}/transactions").build(clientId))
        .retrieve()
        .bodyToFlux(CreditTransaction.class);
  }
}
