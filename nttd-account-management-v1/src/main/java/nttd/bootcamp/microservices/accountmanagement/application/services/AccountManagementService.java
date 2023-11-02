package nttd.bootcamp.microservices.accountmanagement.application.services;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nttd.bootcamp.microservices.accountmanagement.application.mapper.AccountRequestMapper;
import nttd.bootcamp.microservices.accountmanagement.application.mapper.AccountResponseMapper;
import nttd.bootcamp.microservices.accountmanagement.application.usecases.AccountService;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import nttd.bootcamp.microservices.accountmanagement.domain.model.constants.AccountConstant;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountBalanceRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransferRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.enums.AccountType;
import nttd.bootcamp.microservices.accountmanagement.domain.model.enums.ClientType;
import nttd.bootcamp.microservices.accountmanagement.domain.model.enums.ProfileType;
import nttd.bootcamp.microservices.accountmanagement.domain.model.enums.TransactionType;
import nttd.bootcamp.microservices.accountmanagement.domain.port.AccountPersistencePort;
import nttd.bootcamp.microservices.accountmanagement.domain.port.CreditCardServicePort;
import nttd.bootcamp.microservices.accountmanagement.domain.port.CustomerServicePort;
import nttd.bootcamp.microservices.accountmanagement.domain.port.TransactionServicePort;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.exception.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio que implementa los casos de uso de gestión de cuentas bancarias.
 */
@Service
@Log4j2
@AllArgsConstructor
//implementando mis casos de uso
public class AccountManagementService implements AccountService {

  //mis puertos
  private final AccountPersistencePort accountPersistencePort;
  private final CustomerServicePort customerServicePort;
  private final TransactionServicePort transactionServicePort;
  private final CreditCardServicePort creditCardServicePort;
  private final AccountResponseMapper accountResponseMapper;
  private final AccountRequestMapper accountRequestMapper;

  /**
   * Retorna todas las cuentas disponibles.
   *
   * @return Flux de objetos AccountResponse
   */
  @Override
  public Flux<AccountResponse> findAllAccounts() {
    // Retorna todos las cuentas disponibles.
    return accountPersistencePort.getAll().map(accountResponseMapper::toDto);
  }

  /**
   * Retorna una cuenta por su identificador.
   *
   * @param id el identificador de la cuenta
   * @return Mono de objeto AccountResponse
   */
  @Override
  public Mono<AccountResponse> findById(String id) {
    // Retorna una cuenta por su identificador.
    return accountPersistencePort.getById(id).map(accountResponseMapper::toDto);
  }

  /**
   * Retorna todas las cuentas asociadas a un propietario.
   *
   * @param ownerId el identificador del propietario
   * @return Flux de objetos AccountResponse
   */
  @Override
  public Flux<AccountResponse> findByOwnerId(String ownerId) {
    // Retorna todas las cuentas asociadas a un propietario.
    return accountPersistencePort.findByOwnerId(ownerId).map(accountResponseMapper::toDto);
  }

  /**
   * Elimina una cuenta por su identificador.
   *
   * @param id el identificador de la cuenta
   * @return Mono de Void
   */
  @Override
  public Mono<Void> deleteById(String id) {
    // Elimina una cuenta por su identificador.
    return accountPersistencePort.getById(id)
        .flatMap(account -> accountPersistencePort.deleteById(account.getId()))
        .switchIfEmpty(Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
            String.format(AccountConstant.CURRENT_TASK_NOT_ALLOW_TO_DELETE, id))))
        .onErrorResume(throwable ->
            Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                String.format(AccountConstant.CURRENT_OPERATION_FAILED, id)))
        );
  }

  /**
   * Guarda una cuenta personal validando ciertas condiciones.
   *
   * @param request el objeto de tipo AccountRequest a guardar
   * @return Mono de objeto AccountResponse
   */
  @Override
  public Mono<AccountResponse> saveAccountPersonal(AccountRequest request) {
    // Guarda una cuenta personal validando ciertas condiciones.
    return customerServicePort.findClientById(request.getOwnerId())
        .flatMap(client -> {
          if (ClientType.PERSONAL.getDescription().equals(client.getClientType())) {
            if (ProfileType.VIP.getCode().equals(client.getProfileType())) {
              // Verificar que el monto de apertura es al menos 500
              if (request.getBalance() < AccountConstant.MOUNT_MINIMUM_OPENING_AMOUNT_VIP) {
                return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                    String.format(AccountConstant.MINIMUM_OPENING_AMOUNT_VIP,
                        AccountConstant.MOUNT_MINIMUM_OPENING_AMOUNT_VIP)));
              }
              // Verificar que el cliente tiene una tarjeta de crédito
              return creditCardServicePort.hasCreditCard(client.getId())
                  .flatMap(hasCreditCard -> {
                    if (!hasCreditCard) {
                      log.error(AccountConstant.VIP_CLIENT_MUST_HAVE_CREDIT_CARD);
                      return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                          AccountConstant.VIP_CLIENT_MUST_HAVE_CREDIT_CARD));
                    }
                    return processAccountCreation(request, client);
                  });
            } else {
              return processAccountCreation(request, client);
            }
          }
          return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
              AccountConstant.ACCOUNT_REGISTRATION_ERROR));
        })
        .onErrorMap(ex -> {
          log.error(ex);
          if (ex instanceof AccountException) {
            return ex;
          }
          return new AccountException(HttpStatus.INTERNAL_SERVER_ERROR,
              AccountConstant.ACCOUNT_UNEXPECTED_ERROR, ex);
        });
  }

  /**
   * Guarda una cuenta empresarial validando ciertas condiciones.
   *
   * @param request el objeto de tipo AccountRequest a guardar
   * @return Mono de objeto AccountResponse
   */
  @Override
  public Mono<AccountResponse> saveAccountBusiness(AccountRequest request) {
    // Guarda una cuenta empresarial validando ciertas condiciones.
    return customerServicePort.findClientById(request.getOwnerId())
        .flatMap(client -> {
          if (ClientType.BUSINESS.getCode().equals(client.getClientType()) &&
              (AccountType.SAVING_ACCOUNT.getCode().equals(request.getAccountType()) ||
                  AccountType.FIXED_TERM.getCode().equals(request.getAccountType()))) {
            return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                AccountConstant.BUSINESS_CLIENT_ACCOUNT_ERROR));
          }
          if (client.getProfileType() != null && client.getProfileType()
              .equals(ProfileType.PYME.getCode())) {
            if (!AccountType.CURRENT_ACCOUNT.getCode().equals(request.getAccountType())) {
              return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                  AccountConstant.PYME_CLIENTS_MUST_HAVE_CURRENT_ACCOUNT));
            }
            return creditCardServicePort.hasCreditCard(request.getOwnerId())
                .flatMap(hasCreditCard -> {
                  if (!hasCreditCard) {
                    return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                        AccountConstant.PYME_CLIENTS_MUST_HAVE_CREDIT_CARD));
                  }
                  return createAccount(request);
                });
          } else {
            //otro cliente que no tiene perfile de VIP
            return createAccount(request);
          }
        }).onErrorMap(ex -> {
          if (ex instanceof AccountException) {
            return ex;
          }
          return new AccountException(HttpStatus.INTERNAL_SERVER_ERROR,
              AccountConstant.ACCOUNT_UNEXPECTED_ERROR, ex);
        });
  }

  /**
   * Actualiza el saldo de una cuenta.
   *
   * @param id      el identificador de la cuenta
   * @param request el objeto de tipo AccountBalanceRequest con el nuevo saldo
   * @return Mono de objeto AccountResponse
   */
  @Override
  public Mono<AccountResponse> updateBalanceAccount(String id, AccountBalanceRequest request) {
    // Actualiza el saldo de una cuenta.
    return accountPersistencePort.getById(id)
        .switchIfEmpty(Mono.error(
            new AccountException(HttpStatus.NOT_FOUND, AccountConstant.ACCOUNT_NOT_FOUND_MESSAGE)))
        .flatMap(account -> {
          account.setBalance(request.getBalance());
          return accountPersistencePort.update(account)
              .map(accountResponseMapper::toDto);
        })
        .onErrorMap(ex -> {
          if (ex instanceof AccountException) {
            return ex;
          }
          return new AccountException(HttpStatus.INTERNAL_SERVER_ERROR,
              AccountConstant.ACCOUNT_UNEXPECTED_ERROR, ex);
        });
  }


  /**
   * Guarda un movimiento de transferencia entre cuentas validando ciertas condiciones.
   *
   * @param request el objeto de tipo TransferRequest a guardar
   * @return Mono de objeto TransactionResponse
   */
  @Override
  public Mono<TransactionResponse> transferAccount(TransferRequest request) {
    // Realiza una transferencia entre cuentas.
    return accountPersistencePort.getById(request.getFromAccountId())
        .flatMap(fromAccount -> {
          if (fromAccount.getBalance() < request.getAmount()) {
            return Mono.error(
                new AccountException(HttpStatus.BAD_REQUEST, AccountConstant.INSUFFICIENT_FUNDS));
          }
          return accountPersistencePort.getById(request.getToAccountId())
              .flatMap(toAccount -> {
                if (!fromAccount.getBankId().equals(toAccount.getBankId())) {
                  return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                      AccountConstant.INVALID_TRANSFER_BETWEEN_DIFFERENT_BANKS));
                }
                fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
                toAccount.setBalance(toAccount.getBalance() + request.getAmount());
                return accountPersistencePort.update(fromAccount)
                    .then(accountPersistencePort.update(toAccount))
                    .then(transactionServicePort.saveTransaction(
                        Transaction.builder()
                            .toAccountId(request.getToAccountId())
                            .fromAccountId(request.getFromAccountId())
                            .amount(request.getAmount())
                            .transactionDate(LocalDateTime.now())
                            .type(TransactionType.TRANSFER.getCode())
                            .build()
                    ))
                    .map(accountResponseMapper::toDtoTransaction);
              });
        })
        .onErrorMap(ex -> {
          if (ex instanceof AccountException) {
            return ex;
          }
          return new AccountException(HttpStatus.INTERNAL_SERVER_ERROR,
              AccountConstant.ACCOUNT_UNEXPECTED_ERROR, ex);
        });
  }

  private Mono<AccountResponse> processAccountCreation(AccountRequest request, Customer client) {
    // Procesa la creación de una cuenta.
    return accountPersistencePort.findByOwnerId(client.getId())
        .collectList()
        .flatMap(existingAccounts -> {
          long currentAccountsCount = existingAccounts.stream()
              .filter(
                  acc ->
                      acc.getAccountType().equals(AccountType.SAVING_ACCOUNT.getCode()) ||
                          acc.getAccountType().equals(AccountType.CURRENT_ACCOUNT.getCode())
                          ||
                          acc.getAccountType().equals(AccountType.FIXED_TERM.getCode()))
              .count();
          if (currentAccountsCount < 1) {
            return createAccount(request);
          } else {
            return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
                AccountConstant.MAX_SPECIFIED_ACCOUNTS));
          }
        });
  }

  private Mono<AccountResponse> createAccount(AccountRequest request) {
    // Crea una cuenta.
    if (request.getBalance() < request.getMinimumOpeningAmount()) {
      return Mono.error(new AccountException(HttpStatus.BAD_REQUEST,
          AccountConstant.INITIAL_AMOUNT_LESS_THAN_MINIMUM));
    }
    return accountPersistencePort.create(accountRequestMapper.toDomain(request))
        .map(accountResponseMapper::toDto);
  }
}