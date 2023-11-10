package com.yanki.debitcardlinking.domain.port;

import com.yanki.debitcardlinking.domain.model.dto.LinkWalletRequest;
import com.yanki.debitcardlinking.domain.model.dto.LinkWalletResponse;
import reactor.core.publisher.Mono;

public interface DebitCardServicePort {
  Mono<LinkWalletResponse> linkWalletToDebitCard(String cardNumber,
      LinkWalletRequest linkWalletReques);
}
