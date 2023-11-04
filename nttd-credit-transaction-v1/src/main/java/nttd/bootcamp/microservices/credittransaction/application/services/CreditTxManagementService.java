package nttd.bootcamp.microservices.credittransaction.application.services;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.credittransaction.application.mapper.CreditTxRequestMapper;
import nttd.bootcamp.microservices.credittransaction.application.mapper.CreditTxResponseMapper;
import nttd.bootcamp.microservices.credittransaction.application.usecases.CreditTxService;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditTx;
import nttd.bootcamp.microservices.credittransaction.domain.model.constants.CreditTxConstant;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditBalance;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditPaymentRequest;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditTransactionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.enums.TransactionType;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditServicePort;
import nttd.bootcamp.microservices.credittransaction.domain.port.CreditTxPersistencePort;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.CreditTxException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreditTxManagementService implements CreditTxService {

  private final CreditTxPersistencePort creditCardTxPersistencePort;
  private final CreditServicePort creditCardServicePort;
  private final CreditTxRequestMapper creditTxRequestMapper;
  private final CreditTxResponseMapper creditTxResponseMapper;


  @Override
  public Flux<CreditTransactionResponse> getCreditTransactions(String clientId) {
    return creditCardTxPersistencePort.getAllByClientId(clientId)
        .map(creditTxResponseMapper::toDtoCreditTransaction);
  }

  @Override
  public Mono<TransactionResponse> paymentCredit(String creditId, CreditPaymentRequest request) {
    return creditCardServicePort.findCredit(creditId)
        .flatMap(credit -> {
          if (credit.getPendingAmount() < request.getAmount()) {
            String errorMessage = String.format(
                CreditTxConstant.AMOUNT_EXCEEDS_PENDING_CREDIT + " xxRequested: %f, Available: %f",
                request.getAmount(), credit.getPendingAmount());
            return Mono.error(new CreditTxException(HttpStatus.BAD_REQUEST, errorMessage));
          }
          CreditBalance newBalance = CreditBalance.builder()
              .amount(credit.getAmount() + request.getAmount())
              .pendingAmount(credit.getPendingAmount() - request.getAmount())
              .build();
          return creditCardServicePort.refreshBalanceCredit(creditId, newBalance);
        })
        .flatMap(credit -> {
          CreditTx transaction = CreditTx.builder()
              .creditId(creditId)
              .amount(request.getAmount())
              .transactionDate(LocalDateTime.now())
              .type(TransactionType.PAYMENT.getCode())
              .clientId(credit.getClientId())
              .build();
          return creditCardTxPersistencePort.create(transaction)
              .map(creditTxResponseMapper::toDto);
        });
  }
}
