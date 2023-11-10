package com.yanki.debitcardlinking.domain.port;

import com.yanki.debitcardlinking.domain.model.LinkDebitCard;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LinkDebitCardPersistencePort {
  Mono<LinkDebitCard> create(LinkDebitCard model);

  Mono<LinkDebitCard> getById(String id);

  Mono<LinkDebitCard> findByCardNumberAndWalletId(String cardNumber,String WalletId);

  Flux<LinkDebitCard> getAll();

  Mono<Void> deleteById(String id);

  Mono<LinkDebitCard> update(String id , LinkDebitCard model);

  Flux<LinkDebitCard> getByIds(List<String> ids);

}
