package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import nttd.bootcamp.microservices.accounttransaction.domain.model.Transaction;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity.TransactionEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountTransactionDboMapper {


  @Mapping(source = "id", target = "id")
  @Mapping(source = "type", target = "type")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "transactionDate", target = "transactionDate")
  @Mapping(source = "ownerId", target = "ownerId")
  TransactionEntity toDbo(Transaction domain);


  @InheritConfiguration
  Transaction toDomain(TransactionEntity entity);


}
