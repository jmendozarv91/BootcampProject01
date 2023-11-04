package nttd.bootcamp.microservices.credittransaction.application.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.application.usecases.CreditCardTxService;
import nttd.bootcamp.microservices.credittransaction.application.mapper.CreditCardTxRequestMapper;
import nttd.bootcamp.microservices.credittransaction.application.mapper.CreditCardTxResponseMapper;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditCardTx;
import nttd.bootcamp.microservices.credittransaction.domain.model.constants.CreditCardTxConstant;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditCardTransactionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.enums.TransactionType;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditCardServicePort;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditCardTxPersistencePort;
import nttd.bootcamp.microservices.credittransaction.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.CreditCardTxException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreditCardTxManagementService implements CreditCardTxService {

  private final CreditCardTxPersistencePort creditCardTxPersistencePort;
  private final CreditCardServicePort creditCardServicePort;
  private final CreditCardTxRequestMapper creditCardTxRequestMapper;
  private final CreditCardTxResponseMapper creditCardTxResponseMapper;
  private final CustomerServicePort customerServicePort;

  @Override
  public Flux<CreditCardTransactionResponse> getCreditCardTransactions(String clientId) {
    return creditCardTxPersistencePort.getTransactionsByClientId(clientId)
        .map(creditCardTxResponseMapper::toDtoCreditCardTransaction);
  }

  @Override
  @Transactional
  public Mono<ConsumptionResponse> processConsumption(String clientId, String creditCardId,
      ConsumptionRequest request) {
    return creditCardServicePort.getCreditCardById(creditCardId).switchIfEmpty(Mono.error(
            new CreditCardTxException(HttpStatus.BAD_REQUEST,
                String.format(CreditCardTxConstant.CURRENT_CREDIT_CARD_NOT_FOUND, creditCardId))))
        .flatMap(creditCard -> customerServicePort.getClientById(clientId).switchIfEmpty(Mono.error(
                new CreditCardTxException(HttpStatus.BAD_REQUEST,
                    String.format(CreditCardTxConstant.CURRENT_CLIENT_NOT_FOUND, clientId))))
            .flatMap(client -> {
              BigDecimal requestedAmount = BigDecimal.valueOf(request.getAmount());
              BigDecimal availableCredit = creditCard.getCreditLimit()
                  .subtract(creditCard.getAvailableBalance());

              if (requestedAmount.compareTo(availableCredit) > 0) {
                return Mono.error(new CreditCardTxException(HttpStatus.BAD_REQUEST,
                    CreditCardTxConstant.CURRENT_INSUFFICIENT_CREDIT));
              }
              if (creditCard.getAvailableBalance().compareTo(creditCard.getCreditLimit()) == 0) {
                return Mono.error(new CreditCardTxException(HttpStatus.BAD_REQUEST,
                    CreditCardTxConstant.CURRENT_FULL_CREDIT));
              }
              creditCard.setAvailableBalance(creditCard.getAvailableBalance().add(requestedAmount));

              return creditCardServicePort.updateCreditCard(creditCard.getId(), creditCard).then(
                  creditCardTxPersistencePort.create(
                          CreditCardTx.builder().transactionDate(LocalDateTime.now())
                              .amount(request.getAmount()).type(TransactionType.CHARGE.getCode())
                              .clientId(clientId).creditCardId(creditCardId).build())
                      .map(creditCardTxResponseMapper::toDto));
            }));
  }
}
