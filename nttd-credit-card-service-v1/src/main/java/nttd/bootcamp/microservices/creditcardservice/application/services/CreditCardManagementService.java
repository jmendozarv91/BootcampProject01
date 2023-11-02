package nttd.bootcamp.microservices.creditcardservice.application.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.application.mapper.CreditCardResponseMapper;
import nttd.bootcamp.microservices.creditcardservice.application.mapper.CreditCardRequestMapper;
import nttd.bootcamp.microservices.creditcardservice.application.usecases.CreditCardService;
import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import nttd.bootcamp.microservices.creditcardservice.domain.model.Transaction;
import nttd.bootcamp.microservices.creditcardservice.domain.model.constants.CreditCardConstant;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardResponse;
import nttd.bootcamp.microservices.creditcardservice.domain.port.CreditCardPersistencePort;
import nttd.bootcamp.microservices.creditcardservice.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.creditcardservice.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.exception.CreditCardException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio que maneja la lógica de negocio relacionada con las tarjetas de crédito.
 */
@Service
@AllArgsConstructor
public class CreditCardManagementService implements CreditCardService {

  private final CreditCardPersistencePort creditCardPersistencePort;
  private final CustomerServicePort customerServicePort;
  private final TransactionServicePort transactionServicePort;
  private final CreditCardResponseMapper creditCardDtoMapper;
  private final CreditCardRequestMapper creditCardRequestMapper;


  /**
   * Crea una nueva tarjeta de crédito si el cliente no tiene deudas vencidas.
   *
   * @param creditCardRequest los detalles de la tarjeta de crédito a crear.
   * @return un Mono que contiene la respuesta de la creación de la tarjeta de crédito.
   */
  @Override
  public Mono<CreditCardResponse> createNew(CreditCardRequest creditCardRequest) {
    return creditCardPersistencePort.hasOverdueDebts(creditCardRequest.getClientId())
        .flatMap(hasDebts -> {
          if (Boolean.TRUE.equals(hasDebts)) {
            return Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
                CreditCardConstant.OVERDUE_DEBT_ERROR_MESSAGE));
          } else {
            CreditCard creditCardToCreate = creditCardRequestMapper.toDomain(creditCardRequest);
            return creditCardPersistencePort.create(creditCardToCreate)
                .map(creditCardDtoMapper::toDto);
          }
        });
  }

  /**
   * Obtiene una tarjeta de crédito por su ID.
   *
   * @param id el ID de la tarjeta de crédito.
   * @return un Mono que contiene la respuesta con los detalles de la tarjeta de crédito.
   */
  @Override
  public Mono<CreditCardResponse> getCreditCardById(String id) {
    return creditCardPersistencePort.getById(id).map(creditCardDtoMapper::toDto);
  }

  /**
   * Obtiene todas las tarjetas de crédito.
   *
   * @return un Flux que contiene todas las tarjetas de crédito.
   */
  @Override
  public Flux<CreditCardResponse> getAll() {
    return creditCardPersistencePort.getAll().map(creditCardDtoMapper::toDto);
  }

  /**
   * Elimina una tarjeta de crédito por su ID.
   *
   * @param id el ID de la tarjeta de crédito a eliminar.
   * @return un Mono que se completa cuando la tarjeta de crédito ha sido eliminada.
   */
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

  /**
   * Actualiza una tarjeta de crédito por su ID.
   *
   * @param request los detalles de la tarjeta de crédito a actualizar.
   * @param id      el ID de la tarjeta de crédito a actualizar.
   * @return un Mono que contiene la respuesta con los detalles de la tarjeta de crédito
   * actualizada.
   */
  @Override
  public Mono<CreditCardResponse> update(CreditCardRequest request, String id) {
    return creditCardPersistencePort.getById(id)
        .switchIfEmpty(Mono.error(
            new CreditCardException(HttpStatus.NOT_FOUND,
                CreditCardConstant.CURRENT_CREDIT_CARD_NOT_FOUND)))
        .flatMap(existingAccount -> {
          existingAccount.setId(id);
          existingAccount.setAvailableBalance(BigDecimal.valueOf(request.getAvailableBalance()));
          existingAccount.setCreditLimit(BigDecimal.valueOf(request.getCreditLimit()));
          return creditCardPersistencePort.update(existingAccount);
        })
        .map(creditCardDtoMapper::toDto)
        .onErrorResume(ex -> Mono.error(
            new CreditCardException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error")));
  }

  /**
   * Ejecuta un consumo en una tarjeta de crédito.
   *
   * @param clientId     el ID del cliente.
   * @param creditCardId el ID de la tarjeta de crédito.
   * @param request      los detalles del consumo.
   * @return un Mono que se completa cuando el consumo ha sido realizado.
   */
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
              if (BigDecimal.valueOf(request.getAmount()).compareTo(availableCredit) > 0) {
                return Mono.error(new CreditCardException(HttpStatus.BAD_REQUEST,
                    CreditCardConstant.CURRENT_INSUFFICIENT_CREDIT));
              }
              creditCard.setAvailableBalance(
                  creditCard.getAvailableBalance().add(BigDecimal.valueOf(request.getAmount())));

              return creditCardPersistencePort.update(creditCard)
                  .then(transactionServicePort.createTransaction(
                      Transaction.builder()
                          .transactionDate(LocalDateTime.now())
                          .amount(request.getAmount())
                          .type("charger")
                          .clientId(clientId)
                          .creditCardId(creditCardId)
                          .description("aa")
                          .build())).then();

            })

        );
  }

  /**
   * Verifica si un cliente tiene una tarjeta de crédito.
   *
   * @param clientId el ID del cliente.
   * @return un Mono que contiene un booleano que indica si el cliente tiene una tarjeta de crédito.
   */
  @Override
  public Mono<Boolean> hasCreditCard(String clientId) {
    return creditCardPersistencePort.getByClientId(clientId)
        .hasElements()
        .onErrorReturn(false);
  }
}