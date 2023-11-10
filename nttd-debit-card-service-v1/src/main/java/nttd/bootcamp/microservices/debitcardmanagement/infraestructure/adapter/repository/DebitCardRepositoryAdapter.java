package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.constants.DebitCardConstant;
import nttd.bootcamp.microservices.debitcardmanagement.domain.port.DebitCardPersistencePort;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.entity.DebitCardEntity;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception.DebitCardException;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.mapper.DebitCardDboMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DebitCardRepositoryAdapter implements DebitCardPersistencePort {

  private final DebitCardRepository debitCardRepository;
  private final DebitCardDboMapper debitCardDboMapper;

  @Override
  public Mono<DebitCard> create(DebitCard debitCard) {
    DebitCardEntity debitCardEntity = debitCardDboMapper.toDo(debitCard);
    return debitCardRepository.save(debitCardEntity)
        .map(debitCardDboMapper::toDomain);
  }

  @Override
  public Mono<DebitCard> findByCardNumber(String cardNumber) {
    return debitCardRepository.findByCardNumber(cardNumber).map(debitCardDboMapper::toDomain);
  }

  @Override
  public Mono<DebitCard> getById(String id) {
    return debitCardRepository.findById(id).map(debitCardDboMapper::toDomain);
  }

  @Override
  public Flux<DebitCard> getAll() {
    return debitCardRepository.findAll().map(debitCardDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<DebitCard> update(DebitCard debitCard) {
    return debitCardRepository.findById(debitCard.getId())
        .switchIfEmpty(Mono.error(
            new DebitCardException(HttpStatus.NOT_FOUND, DebitCardConstant.ACCOUNT_NOT_FOUND_MESSAGE)))
        .flatMap(debitCardEntity -> {
          debitCardEntity.setWalletId(debitCard.getWalletId());
          return debitCardRepository.save(debitCardEntity);
        })
        .map(debitCardDboMapper::toDomain)
        .onErrorResume(ex -> Mono.error(
            new DebitCardException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex)));
  }

  @Override
  public Flux<DebitCard> getByIds(List<String> accountIds) {
    return null;
  }
}
