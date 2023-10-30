package nttd.bootcamp.microservices.accountmanagement.application.usecases;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Customer;
import reactor.core.publisher.Mono;

/**
 * Servicio para la gestión de clientes.
 */
public interface CustomerService {

  /**
   * Busca un cliente por su identificador.
   *
   * @param clientId el identificador del cliente.
   * @return Mono de Customer, el cliente encontrado.
   */
  Mono<Customer> findClientById(String clientId);
}