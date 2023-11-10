package com.yanki.debitcardlinking.infraestructure.adapters.external;

import com.yanki.debitcardlinking.domain.model.dto.LinkWalletRequest;
import com.yanki.debitcardlinking.domain.model.dto.LinkWalletResponse;
import com.yanki.debitcardlinking.domain.port.DebitCardServicePort;
import com.yanki.debitcardlinking.infraestructure.adapters.config.properties.DebitCardProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class DebitCardServiceAdapter implements DebitCardServicePort {

  private final WebClient webClient;

  public DebitCardServiceAdapter(WebClient.Builder webClientBuilder,
      DebitCardProperties debitCardProperties) {
    this.webClient = webClientBuilder.baseUrl(debitCardProperties.getBaseUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Override
  public Mono<LinkWalletResponse> linkWalletToDebitCard(String cardNumber,
      LinkWalletRequest linkWalletRequest) {
    log.info("cardNumber =" + cardNumber);
    return webClient.post().uri(uriBuilder -> uriBuilder.path(cardNumber).path("/link-wallet").build())
        .bodyValue(linkWalletRequest).retrieve()
        .bodyToMono(LinkWalletResponse.class);
  }
}
