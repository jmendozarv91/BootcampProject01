package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.mapper;

import nttd.bootcamp.microservices.credittransaction.domain.model.CreditCardTx;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardTxDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "clientId", target = "clientId")
  @Mapping(source = "type", target = "type")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "accountId", target = "accountId")
  @Mapping(source = "transactionDate", target = "transactionDate")
  @Mapping(source = "creditCardId", target = "creditCardId")
  TransactionEntity toDbo(CreditCardTx domain);

  @InheritConfiguration
  CreditCardTx toDomain(TransactionEntity entity);
}
