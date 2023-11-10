package com.yanki.debitcardlinking.infraestructure.adapters.repository;

import com.yanki.debitcardlinking.domain.model.LinkDebitCard;
import com.yanki.debitcardlinking.domain.port.LinkDebitCardPersistencePort;
import com.yanki.debitcardlinking.infraestructure.adapters.entity.LinkDebitCardEntity;
import com.yanki.debitcardlinking.infraestructure.adapters.mapper.LinkDebitDboMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class LinkDebitCardRepositoryAdapter implements LinkDebitCardPersistencePort {

  private final LinkDebitCardRepository linkDebitCardRepository;
  private final LinkDebitDboMapper linkDebitDboMapper;

  @Override
  public Mono<LinkDebitCard> create(LinkDebitCard model) {
    LinkDebitCardEntity debitCardEntity = linkDebitDboMapper.toDbo(model);
    return linkDebitCardRepository.save(debitCardEntity).map(linkDebitDboMapper::toDomain);
  }

  @Override
  public Mono<LinkDebitCard> findByCardNumberAndWalletId(String cardNumber, String walletId) {
    return linkDebitCardRepository.findByCardNumberAndWalletId(cardNumber, walletId)
        .map(linkDebitDboMapper::toDomain);
  }

  @Override
  public Mono<LinkDebitCard> getById(String id) {
    return linkDebitCardRepository.findById(id).map(linkDebitDboMapper::toDomain);
  }

  @Override
  public Flux<LinkDebitCard> getAll() {
    return linkDebitCardRepository.findAll().map(linkDebitDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<LinkDebitCard> update(String id, LinkDebitCard model) {
    return null;
  }

  @Override
  public Flux<LinkDebitCard> getByIds(List<String> ids) {
    return null;
  }
}
