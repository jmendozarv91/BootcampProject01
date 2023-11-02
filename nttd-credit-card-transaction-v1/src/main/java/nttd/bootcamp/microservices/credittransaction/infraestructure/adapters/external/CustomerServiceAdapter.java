package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.external;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.Customer;
import nttd.bootcamp.microservices.credittransaction.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.config.properties.CustomerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceAdapter implements CustomerServicePort {

  private final WebClient webClient;

  public CustomerServiceAdapter(WebClient.Builder webClientBuilder,
      CustomerProperties customerProperties) {
    this.webClient = webClientBuilder.baseUrl(customerProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Mono<Customer> getClientById(String id) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(id).build())
        .retrieve()
        .bodyToMono(Customer.class);
  }
}
