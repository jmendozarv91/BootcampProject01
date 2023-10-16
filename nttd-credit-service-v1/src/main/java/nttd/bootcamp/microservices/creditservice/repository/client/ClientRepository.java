package nttd.bootcamp.microservices.creditservice.repository.client;

import nttd.bootcamp.microservices.creditservice.dto.ClientDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepository {
    private final WebClient webClient;

    public ClientRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8087")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public Mono<ClientDto> getClient(String clientId){
        System.out.println("clientId : " + clientId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/management-client/")
                        .path(clientId)
                        .build())
                .retrieve()  // inicia la solicitud
                .bodyToMono(ClientDto.class);
    }


}
