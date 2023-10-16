package nttd.bootcamp.microservices.account.management.repository.client;

import nttd.bootcamp.microservices.account.management.dto.ClienteDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepository {
    private final WebClient webClient;

    public ClientRepository(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8087")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public Mono<ClienteDto> findClientById(String parametro) {
        System.out.println("parametro : " + parametro);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/management-client/")
                        .path(parametro)
                        .build())
                .retrieve()  // inicia la solicitud
                .bodyToMono(ClienteDto.class);
    }
}
