package nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository.client;

import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.CreditBalanceDto;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.CreditDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class CreditRepository {
    private final WebClient webClient;

    public CreditRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }


    public Mono<CreditDto> findCredit(String creditId){
        System.out.println("creditId : " + creditId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/credit-service/credits/")
                        .path(creditId)
                        .build())
                .retrieve()  // inicia la solicitud
                .bodyToMono(CreditDto.class);
    }

    public Mono<CreditDto> refreshBalanceCredit(String creditId , CreditBalanceDto creditBalanceDto){
        System.out.println("creditDto : " + creditBalanceDto.toString());
        System.out.println("creditId : " + creditId);
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/credit-service/credits/")
                        .path(creditId)
                        .path("/refresh-balance")
                        .build())
                .bodyValue(creditBalanceDto)
                .retrieve()  // inicia la solicitud
                .bodyToMono(CreditDto.class);
    }




}
