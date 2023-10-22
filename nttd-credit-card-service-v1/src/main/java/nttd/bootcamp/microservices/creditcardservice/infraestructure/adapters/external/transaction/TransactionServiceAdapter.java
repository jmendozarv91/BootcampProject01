package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.external.transaction;

import nttd.bootcamp.microservices.creditcardservice.domain.model.Transaction;
import nttd.bootcamp.microservices.creditcardservice.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.config.properties.TransactionProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceAdapter implements TransactionServicePort {

  private final WebClient webClient;

  public TransactionServiceAdapter(WebClient.Builder webClientBuilder,
      TransactionProperties transactionProperties) {
    this.webClient = webClientBuilder.baseUrl(transactionProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }


  @Override
  public Mono<Transaction> createTransaction(Transaction transaction) {
    System.out.println("enviado");
    System.out.println(transaction);
    return webClient.post().uri(uriBuilder -> uriBuilder.path("transaction").build())
        .bodyValue(transaction).retrieve()
        .bodyToMono(Transaction.class);
  }
}
