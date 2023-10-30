package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountBalanceRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransferRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para la gestión de cuentas bancarias.
 */
public interface AccountService {

  /**
   * Retorna todas las cuentas disponibles.
   *
   * @return Flux de objetos AccountResponse
   */
  Flux<AccountResponse> findAllAccounts();

  /**
   * Retorna una cuenta por su identificador.
   *
   * @param id el identificador de la cuenta
   * @return Mono de objeto AccountResponse
   */
  Mono<AccountResponse> findById(String id);

  /**
   * Retorna todas las cuentas asociadas a un propietario.
   *
   * @param ownerId el identificador del propietario
   * @return Flux de objetos AccountResponse
   */
  Flux<AccountResponse> findByOwnerId(String ownerId);

  /**
   * Elimina una cuenta por su identificador.
   *
   * @param id el identificador de la cuenta
   * @return Mono de Void
   */
  Mono<Void> deleteById(String id);

  /**
   * Guarda una cuenta personal validando ciertas condiciones.
   *
   * @param request el objeto de tipo AccountRequest a guardar
   * @return Mono de objeto AccountResponse
   */
  Mono<AccountResponse> saveAccountPersonal(AccountRequest request);

  /**
   * Guarda una cuenta empresarial validando ciertas condiciones.
   *
   * @param request el objeto de tipo AccountRequest a guardar
   * @return Mono de objeto AccountResponse
   */
  Mono<AccountResponse> saveAccountBusiness(AccountRequest request);

  /**
   * Actualiza el saldo de una cuenta.
   *
   * @param id      el identificador de la cuenta
   * @param request el objeto de tipo AccountBalanceRequest con el nuevo saldo
   * @return Mono de objeto AccountResponse
   */
  Mono<AccountResponse> updateBalanceAccount(String id, AccountBalanceRequest request);

  /**
   * Guarda un movimiento de transferencia entre cuentas validando ciertas condiciones.
   *
   * @param request el objeto de tipo TransferRequest a guardar
   * @return Mono de objeto TransactionResponse
   */
  Mono<TransactionResponse> transferAccount(TransferRequest request);
}