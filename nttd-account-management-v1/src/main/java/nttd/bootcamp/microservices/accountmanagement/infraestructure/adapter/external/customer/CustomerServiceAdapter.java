package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.external.customer;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import nttd.bootcamp.microservices.accountmanagement.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.config.properties.CustomerProperties;
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
  public Mono<Customer> findClientById(String clientId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(clientId).build())
        .retrieve()
        .bodyToMono(Customer.class);
  }
}
