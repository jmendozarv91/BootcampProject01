package nttd.bootcamp.microservices.credittransaction.application.mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditTx;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring")
public interface CreditTxResponseMapper {

  @Mapping(source = "creditId", target = "creditId")
  @Mapping(source = "type", target = "type")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "transactionDate", target = "transactionDate", qualifiedBy = ToOffsetDateTime.class)
  TransactionResponse toDto(CreditTx model);


  @ToOffsetDateTime
  default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
    return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface ToOffsetDateTime {

  }
}
