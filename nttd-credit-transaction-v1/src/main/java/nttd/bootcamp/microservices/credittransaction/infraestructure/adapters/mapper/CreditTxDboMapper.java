package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.mapper;

import nttd.bootcamp.microservices.credittransaction.domain.model.CreditTx;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity.TransactionEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditTxDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "type", target = "type")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "transactionDate", target = "transactionDate")
  TransactionEntity toDbo(CreditTx domain);

  @InheritConfiguration
  CreditTx toDomain(TransactionEntity entity);
}
