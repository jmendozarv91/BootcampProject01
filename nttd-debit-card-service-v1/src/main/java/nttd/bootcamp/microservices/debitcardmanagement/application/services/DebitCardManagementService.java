package nttd.bootcamp.microservices.debitcardmanagement.application.services;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nttd.bootcamp.microservices.debitcardmanagement.application.mapper.DebitCardRequestMapper;
import nttd.bootcamp.microservices.debitcardmanagement.application.mapper.DebitCardResponseMapper;
import nttd.bootcamp.microservices.debitcardmanagement.application.usecases.DebitCardService;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.constants.DebitCardConstant;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.Account;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionRequest;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.PerformTransactionResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.port.AccountServicePort;
import nttd.bootcamp.microservices.debitcardmanagement.domain.port.DebitCardPersistencePort;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception.BadRequestException;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception.DebitCardException;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Log4j2
public class DebitCardManagementService implements DebitCardService {

  private final DebitCardPersistencePort debitCardPersistencePort;
  private final AccountServicePort accountServicePort;
  private final DebitCardResponseMapper debitCardResponseMapper;

  @Override
  public Mono<CreateDebitCardResponse> createDebitCard(CreateDebitCardRequest debitCardRequest) {
    // Validar que la cuenta principal exista y esté asociada al cliente
    Mono<Account> mainAccount = accountServicePort.findById(debitCardRequest.getAccountNumber())
        .switchIfEmpty(Mono.error(new DebitCardException(HttpStatus.BAD_REQUEST,
            DebitCardConstant.MAIN_ACCOUNT_NOT_EXIST))  );

    // Validar que las cuentas asociadas existan y estén asociadas al cliente
    Flux<Account> associatedAccounts = Flux.fromIterable(debitCardRequest.getAssociatedAccounts())
        .flatMap(accountNumber -> accountServicePort.findById(accountNumber)
            .switchIfEmpty(Mono.error(new DebitCardException(HttpStatus.BAD_REQUEST,
                DebitCardConstant.ASSOCIATED_ACCOUNT_NOT_EXIST))));

    // Crear la tarjeta de débito y asociarla a las cuentas
    return mainAccount
        .flatMap(mainAcc -> associatedAccounts.collectList()
            .flatMap(assocAccs -> {
              DebitCard debitCard = new DebitCard();
              debitCard.setPrimaryAccountId(mainAcc.getId());
              debitCard.setAssociatedAccountIds(assocAccs.stream().map(Account::getId).collect(
                  Collectors.toList()));
              return debitCardPersistencePort.create(debitCard).map(debitCardResponseMapper::toDto);
            }))
        .doOnSuccess(card -> log.info("Debit card created successfully: {}", card.getCardNumber()))
        .doOnError(e -> log.error("Error creating debit card", e));
  }

  @Override
  public Flux<CreateDebitCardResponse> getAllDebitCards() {
    return debitCardPersistencePort.getAll()
        .map(debitCardResponseMapper::toDto);
  }

  @Override
  public Mono<Void> associateDebitCardWithAccount(String cardNumber, String accountNumber) {
    return debitCardPersistencePort.findByCardNumber(cardNumber)
        .switchIfEmpty(Mono.error(new NotFoundException(DebitCardConstant.DEBIT_CARD_NOT_FOUND_MESSAGE)))
        .flatMap(debitCard -> accountServicePort.findById(accountNumber)
            .switchIfEmpty(Mono.error(new NotFoundException(DebitCardConstant.ACCOUNT_NOT_FOUND_MESSAGE)))
            .flatMap(account -> {
              if (debitCard.getAssociatedAccountIds().contains(account.getId())) {
                return Mono.error(new BadRequestException(DebitCardConstant.ACCOUNT_ALREADY_ASSOCIATED));
              }
              debitCard.getAssociatedAccountIds().add(account.getId());
              return debitCardPersistencePort.update(debitCard)
                  .then();
            })
        );
  }


  @Override
  public Mono<PerformTransactionResponse> performTransaction(String cardNumber,
      PerformTransactionRequest transactionRequest) {
    return null;
  }
}
