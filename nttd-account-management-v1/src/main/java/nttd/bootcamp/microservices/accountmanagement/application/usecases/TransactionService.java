package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Servicio para la gestión de transacciones.
 */
public interface TransactionService {

  /**
   * Guarda una transacción en el sistema.
   *
   * @param transaction la transacción a guardar.
   * @return Mono de Transaction, la transacción guardada.
   */
  Mono<Transaction> saveTransaction(Transaction transaction);
}