package com.yanki.debitcardlinking.application.services;

import com.yanki.debitcardlinking.application.mapper.LinkDebitCardMapper;
import com.yanki.debitcardlinking.application.usecases.LinkDebitCardService;
import com.yanki.debitcardlinking.domain.model.LinkDebitCard;
import com.yanki.debitcardlinking.domain.model.Wallet;
import com.yanki.debitcardlinking.domain.model.dto.LinkWalletRequest;
import com.yanki.debitcardlinking.domain.port.DebitCardServicePort;
import com.yanki.debitcardlinking.domain.port.LinkDebitCardPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@AllArgsConstructor
public class LinkDebitCardManagementService implements LinkDebitCardService {

  private final LinkDebitCardPersistencePort linkDebitCardPersistencePort;
  private final LinkDebitCardMapper linkDebitCardMapper;
  //servicio externo
  private final DebitCardServicePort debitCardServicePort;

  @Override
  public Mono<LinkDebitCard> associateDebitCardWithWallet(String walletId, String debitCardNumber,
      String accountId) {
    log.info("Associating debit card {} with wallet {}", debitCardNumber, walletId);

    return linkDebitCardPersistencePort.findByCardNumberAndWalletId(debitCardNumber, walletId)
        .flatMap(linkDebitCard -> {
          log.info("Debit card {} already associated with wallet {}", debitCardNumber, walletId);
          return Mono.just(linkDebitCard);
        })
        .switchIfEmpty(Mono.defer(() -> {
              log.info("Creating new link debit card for wallet {}", walletId);
              LinkDebitCard linkDebitCard = createLinkDebitCard(walletId, debitCardNumber, accountId);
              return linkDebitCardPersistencePort.create(linkDebitCard)
                  .flatMap(savedLinkDebitCard -> {
                    log.info("Created new link debit card for wallet {}", walletId);
                    return debitCardServicePort.linkWalletToDebitCard(debitCardNumber,
                            createLinkWalletRequest(walletId, accountId))
                        .thenReturn(savedLinkDebitCard);
                  });
            })
            .onErrorResume(WebClientResponseException.class, e -> {
              log.error("Error associating debit card {} with wallet {}", debitCardNumber, walletId,
                  e);
              return Mono.error(new RuntimeException("Error associating debit card with wallet"));
            }));
  }


  private LinkDebitCard createLinkDebitCard(String walletId, String debitCardNumber,
      String accountId) {
    LinkDebitCard linkDebitCard = new LinkDebitCard();
    linkDebitCard.setWallet(Wallet.builder().id(walletId).build());
    linkDebitCard.setCardNumber(debitCardNumber);
    linkDebitCard.setBankId(accountId);
    return linkDebitCard;
  }

  private LinkWalletRequest createLinkWalletRequest(String walletId, String accountId) {
    return LinkWalletRequest.builder()
        .walletId(walletId)
        .accountId(accountId)
        .build();
  }

}
