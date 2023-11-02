package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.external;

import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.Account;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.AccountBalance;
import nttd.bootcamp.microservices.accounttransaction.domain.port.AccountServicePort;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.config.properties.AccountProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceAdapter implements AccountServicePort {

  private final WebClient webClient;

  public AccountServiceAdapter(WebClient.Builder webClientBuilder,
      AccountProperties accountProperties) {
    this.webClient = webClientBuilder.baseUrl(accountProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }


  public Mono<Account> findAccountById(String accountId) {
    System.out.println("accountId : " + accountId);
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(accountId)
            .build())
        .retrieve()  // inicia la solicitud
        .bodyToMono(Account.class);
  }

  public Mono<Account> modifyAccountBalance(String idAccount, Account account) {
    return webClient.patch()
        .uri(uriBuilder -> uriBuilder.path("modify-balance/")
            .path(idAccount).build())
        .bodyValue(AccountBalance.builder().balance(account.getBalance()).build())
        .retrieve()
        .bodyToMono(Account.class);
  }


}
