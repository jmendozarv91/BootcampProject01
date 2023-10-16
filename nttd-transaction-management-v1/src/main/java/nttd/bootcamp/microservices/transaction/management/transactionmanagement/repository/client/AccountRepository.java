package nttd.bootcamp.microservices.transaction.management.transactionmanagement.repository.client;

import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.AccountBalanceDto;
import nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto.AccountDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountRepository {
    private final WebClient webClient;

    public AccountRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8086")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }


    public Mono<AccountDto> findAccountById(String parametro){
        System.out.println("parametro : " + parametro);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/account/")
                        .path(parametro)
                        .build())
                .retrieve()  // inicia la solicitud
                .bodyToMono(AccountDto.class);
    }

    public Mono<AccountDto> modifyAccountBalance(String idAccount , AccountDto accountDto){
        AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
        accountBalanceDto.setBalance(accountDto.getBalance());
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/account/")
                        .path(idAccount).build())
                .bodyValue(accountBalanceDto)
                .retrieve()
                .bodyToMono(AccountDto.class);
    }








}
