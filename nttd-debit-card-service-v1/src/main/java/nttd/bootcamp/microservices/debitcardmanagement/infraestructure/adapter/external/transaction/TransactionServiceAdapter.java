package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.external.transaction;

import nttd.bootcamp.microservices.debitcardmanagement.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.config.properties.TransactionProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceAdapter implements TransactionServicePort {

  private WebClient webClient;

  public TransactionServiceAdapter(final WebClient.Builder webClientBuilder,
      TransactionProperties transactionProperties) {
    WebClient webClient = webClientBuilder.baseUrl(transactionProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Mono<Object> performTransaction(String id, Double amount, String transactionType) {
    return null;
  }
}
