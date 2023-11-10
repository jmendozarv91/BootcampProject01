package com.yanki.debitcardlinking.application.usecases;

import com.yanki.debitcardlinking.domain.model.LinkDebitCard;
import reactor.core.publisher.Mono;

public interface LinkDebitCardService {

  Mono<LinkDebitCard> associateDebitCardWithWallet(String walletId, String debitCardNumber,
      String accountId);
}
