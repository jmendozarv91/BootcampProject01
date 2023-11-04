package nttd.bootcamp.microservices.credittransaction.application.mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import nttd.bootcamp.microservices.credittransaction.domain.model.CreditCardTx;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.ConsumptionResponse;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditCardTransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring")
public interface CreditCardTxResponseMapper {

  @Mapping(target = "transactionId", source = "id")
  @Mapping(target = "clientId", source = "clientId")
  @Mapping(target = "creditCardId", source = "creditCardId")
  @Mapping(target = "amount", source = "amount")
  @Mapping(target = "transactionDate", source = "transactionDate", qualifiedBy = ToOffsetDateTime.class)
  ConsumptionResponse toDto(CreditCardTx domain);

  @Mapping(target = "transactionId", source = "id")
  @Mapping(target = "creditCardId", source = "creditCardId")
  @Mapping(target = "amount", source = "amount")
  @Mapping(target = "transactionDate", source = "transactionDate", qualifiedBy = ToOffsetDateTime.class)
  CreditCardTransactionResponse toDtoCreditCardTransaction(CreditCardTx domain);

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
