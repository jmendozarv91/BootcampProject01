package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.external.transaction;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import nttd.bootcamp.microservices.accountmanagement.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.config.properties.TransactionProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
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
  public Mono<Transaction> saveTransaction(Transaction transaction) {
    return webClient.post().uri(uriBuilder -> uriBuilder.path("transaction").build())
        .bodyValue(transaction).retrieve()
        .bodyToMono(Transaction.class);
  }
}
