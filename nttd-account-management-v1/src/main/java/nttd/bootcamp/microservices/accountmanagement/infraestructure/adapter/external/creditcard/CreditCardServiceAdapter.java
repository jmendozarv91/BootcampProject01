package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.external.creditcard;

import nttd.bootcamp.microservices.accountmanagement.domain.port.CreditCardServicePort;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.config.properties.CreditCardProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceAdapter implements CreditCardServicePort {

  private final WebClient webClient;

  public CreditCardServiceAdapter(WebClient.Builder webClientBuilder,
      CreditCardProperties creditCardProperties) {
    this.webClient = webClientBuilder.baseUrl(creditCardProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Mono<Boolean> hasCreditCard(String clientId) {
    return webClient.get()
        .uri( "/hasCreditCard?clientId=" + clientId)
        .retrieve()
        .bodyToMono(Boolean.class)
        .onErrorReturn(false); // Retorna false en caso de error
  }
}
