package nttd.bootcamp.microservices.creditcardservice.application.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.application.mapper.CreditCardDtoMapper;
import nttd.bootcamp.microservices.creditcardservice.application.mapper.CreditCardRequestMapper;
import nttd.bootcamp.microservices.creditcardservice.application.usecases.CreditCardService;
import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import nttd.bootcamp.microservices.creditcardservice.domain.model.Transaction;
import nttd.bootcamp.microservices.creditcardservice.domain.model.constants.CreditCardConstant;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardDto;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.CreditCardRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.port.CreditCardPersistencePort;
import nttd.bootcamp.microservices.creditcardservice.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.creditcardservice.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.exception.CreditCardException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreditCardManagementService implements CreditCardService {

  private final CreditCardPersistencePort creditCardPersistencePort;
  private final CustomerServicePort customerServicePort;
  private final TransactionServicePort transactionServicePort;
  private final CreditCardDtoMapper creditCardDtoMapper;
  private final CreditCardRequestMapper creditCardRequestMapper;

  @Override
  public Mono<CreditCardDto> createNew(CreditCardRequest creditCardRequest) {
    CreditCard creditCardToCreate = creditCardRequestMapper.toDomain(creditCardRequest);
    return creditCardPersistencePort.create(creditCardToCreate).map(creditCardDtoMapper::toDto);
  }

  @Override
  public Mono<CreditCardDto> getById(String id) {
    return creditCardPersistencePort.getById(id).map(creditCardDtoMapper::toDto);
  }

  @Override
  public Flux<CreditCardDto> getAll() {
    return creditCardPersistencePort.getAll().map(creditCardDtoMapper::toDto);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return creditCardPersistencePort.getById(id)
        .flatMap(creditCard -> creditCardPersistencePort.deleteById(creditCard.getId()))
        .switchIfEmpty(Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
            String.format(CreditCardConstant.CURRENT_TASK_NOT_ALLOW_TO_DELETE, id))))
        .onErrorResume(throwable ->
            Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
                String.format(CreditCardConstant.CURRENT_OPERATION_FAILED, id)))
        );
  }

  @Override
  public Mono<CreditCardDto> update(CreditCardRequest request, String id) {
    CreditCard creditCardToUpdate = creditCardRequestMapper.toDomain(request);
    creditCardToUpdate.setCreditLimit(request.getCreditLimit());
    creditCardToUpdate.setAvailableBalance(request.getAvailableBalance());
    return creditCardPersistencePort.update(creditCardToUpdate).map(creditCardDtoMapper::toDto);
  }


  @Override
  public Mono<Void> executeConsumption(String clientId, String creditCardId,
      ConsumptionRequest request) {
    return creditCardPersistencePort.getById(creditCardId)
        .switchIfEmpty(Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
            String.format(CreditCardConstant.CURRENT_CREDIT_CARD_NOT_FOUND, creditCardId))))
        .flatMap(creditCard -> customerServicePort.getClientById(creditCard.getClientId())
            .flatMap(validClient -> {
              BigDecimal availableCredit = creditCard.getCreditLimit()
                  .subtract(creditCard.getAvailableBalance());
              if (request.getAmount().compareTo(availableCredit) > 0) {
                return Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
                    CreditCardConstant.CURRENT_INSUFFICIENT_CREDIT));
              }
              creditCard.setAvailableBalance(
                  creditCard.getAvailableBalance().add(request.getAmount()));

              return creditCardPersistencePort.update(creditCard)
                  .then(transactionServicePort.createTransaction(
                      Transaction.builder()
                          .transactionDate(LocalDateTime.now())
                          .amount(request.getAmount().doubleValue())
                          .type("charger")
                          .clientId(clientId)
                          .creditCardId(creditCardId)
                          .description("aa")
                          .build())).then();

            })

        );

  }
}
