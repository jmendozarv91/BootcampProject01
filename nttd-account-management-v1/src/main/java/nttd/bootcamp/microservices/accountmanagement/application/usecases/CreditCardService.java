package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import reactor.core.publisher.Mono;

/**
 * Servicio para la gestión de tarjetas de crédito.
 */
public interface CreditCardService {

  /**
   * Verifica si un cliente tiene al menos una tarjeta de crédito asociada.
   *
   * @param clientId el identificador del cliente
   * @return Mono de Boolean, true si el cliente tiene al menos una tarjeta de crédito, false en caso contrario
   */
  Mono<Boolean> hasCreditCard(String clientId);
}
