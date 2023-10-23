package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.mapper;

import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.entities.AccountEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDboMapper {
  @Mapping(source = "id", target = "id")
  @Mapping(source = "accountNumber", target = "accountNumber")
  @Mapping(source = "accountType", target = "accountType")
  @Mapping(source = "balance", target = "balance")
  @Mapping(source = "maxMonthlyTransactions", target = "maxMonthlyTransactions")
  @Mapping(source = "maintenanceFee", target = "maintenanceFee")
  @Mapping(source = "ownerId", target = "ownerId")
  @Mapping(source = "minimumOpeningAmount", target = "minimumOpeningAmount")
  AccountEntity toDbo(Account domain);


  @InheritConfiguration
  Account toDomain(AccountEntity entity);
}
