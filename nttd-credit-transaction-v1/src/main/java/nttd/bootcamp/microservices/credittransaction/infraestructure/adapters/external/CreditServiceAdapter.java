package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.external;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.Credit;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditBalance;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditServicePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.config.properties.CreditProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceAdapter implements CreditServicePort {

  private final WebClient webClient;

  public CreditServiceAdapter(WebClient.Builder webClientBuilder,
      CreditProperties creditProperties) {
    this.webClient = webClientBuilder.baseUrl(creditProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }


  @Override
  public Mono<Credit> findCredit(String creditId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(creditId).build())
        .retrieve()
        .bodyToMono(Credit.class);
  }

  @Override
  public Mono<Credit> refreshBalanceCredit(String creditId, CreditBalance creditBalance) {
    return webClient.patch()
        .uri(uriBuilder -> uriBuilder.path(creditId).path("/refresh-balance")
            .build())
        .bodyValue(creditBalance)
        .retrieve()  // inicia la solicitud
        .bodyToMono(Credit.class);
  }
}
