package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.external;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditCard;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditCardServicePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.config.properties.CreditCardProperties;
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
  public Mono<CreditCard> getCreditCardById(String id) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(id).build())
        .retrieve()
        .bodyToMono(CreditCard.class);
  }

  @Override
  public Mono<CreditCard> updateCreditCard(String id, CreditCard request) {
    return webClient.put().uri(uriBuilder -> uriBuilder.path(id).build())
        .bodyValue(request).retrieve()
        .bodyToMono(CreditCard.class);
  }
}
