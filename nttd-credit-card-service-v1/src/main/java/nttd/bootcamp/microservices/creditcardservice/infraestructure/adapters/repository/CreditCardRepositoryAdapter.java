package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import nttd.bootcamp.microservices.creditcardservice.domain.port.CreditCardPersistencePort;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.entities.CreditCardEntity;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.mapper.CreditCardDboMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class CreditCardRepositoryAdapter implements CreditCardPersistencePort {

  private final CreditCardRepository creditCardRepository;

  private final CreditCardDboMapper creditCardDboMapper;

  @Override
  public Mono<CreditCard> create(CreditCard request) {
    CreditCardEntity creditCardToSave = creditCardDboMapper.toDbo(request);
    return creditCardRepository.save(creditCardToSave).map(creditCardDboMapper::toDomain);
  }

  @Override
  public Mono<CreditCard> getById(String id) {
    return creditCardRepository.findById(id).map(creditCardDboMapper::toDomain);
  }

  @Override
  public Flux<CreditCard> getAll() {
    return null;
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return null;
  }

  @Override
  public Mono<CreditCard> update(CreditCard creditCard) {
    CreditCardEntity creditCardEntity = creditCardDboMapper.toDbo(creditCard);

    return creditCardRepository.save(creditCardEntity).map(creditCardDboMapper::toDomain);
  }

  @Override
  public Flux<CreditCard> getByIds(List<String> creditCardIds) {
    return null;
  }

  @Override
  public Flux<CreditCard> getByClientId(String clientId) {
    return creditCardRepository.findByClientId(clientId).map(creditCardDboMapper::toDomain)
        .onErrorResume(e -> {
          return Flux.empty();  // Devuelve un Flux vacío en caso de error
        });
  }

  @Override
  public Mono<Boolean> hasOverdueDebts(String clientId) {
    return creditCardRepository.findByClientId(clientId)
        .any(credit -> credit.getDueDate().toLocalDate().isBefore(LocalDate.now())
            && credit.getAvailableBalance().compareTo(BigDecimal.ZERO) > 0);
  }
}
