package nttd.bootcamp.microservices.accountmanagement.application.mapper;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para convertir entre objetos de tipo AccountRequest y Account.
 */
@Mapper(componentModel = "spring")
public interface AccountRequestMapper {

  /**
   * Mapea un objeto de tipo AccountRequest a un objeto de tipo Account.
   *
   * @param request el objeto de tipo AccountRequest a mapear.
   * @return el objeto de tipo Account mapeado.
   */
  @Mapping(source = "id", target = "id")
  @Mapping(source = "accountNumber", target = "accountNumber")
  @Mapping(source = "accountType", target = "accountType")
  @Mapping(source = "balance", target = "balance")
  @Mapping(source = "maxMonthlyTransactions", target = "maxMonthlyTransactions")
  @Mapping(source = "maintenanceFee", target = "maintenanceFee")
  @Mapping(source = "ownerId", target = "ownerId")
  @Mapping(source = "minimumOpeningAmount", target = "minimumOpeningAmount")
  Account toDomain(AccountRequest request);
}
