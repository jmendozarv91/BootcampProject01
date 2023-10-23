package nttd.bootcamp.microservices.accountmanagement.application.mapper;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountRequestMapper {
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
