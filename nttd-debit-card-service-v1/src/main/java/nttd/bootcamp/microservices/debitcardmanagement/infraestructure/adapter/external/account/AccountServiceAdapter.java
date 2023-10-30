package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.external.account;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.Account;
import nttd.bootcamp.microservices.debitcardmanagement.domain.port.AccountServicePort;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.config.properties.AccountProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceAdapter implements AccountServicePort {

  private WebClient webClient;

  public AccountServiceAdapter(final WebClient.Builder webClientBuilder,
      AccountProperties accountProperties) {
    WebClient webClient = webClientBuilder.baseUrl(accountProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Mono<Account> findById(String id) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(id).build())
        .retrieve()
        .bodyToMono(Account.class);
  }
}
