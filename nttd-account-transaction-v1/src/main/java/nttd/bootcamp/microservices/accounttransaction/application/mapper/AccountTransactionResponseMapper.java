package nttd.bootcamp.microservices.accounttransaction.application.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import nttd.bootcamp.microservices.accounttransaction.domain.model.Transaction;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionResponse.TransactionTypeEnum;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountTransactionResponseMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "accountId", target = "accountId")
  @Mapping(source = "type", target = "transactionType", qualifiedByName = "stringToTransactionTypeEnum")
  @Mapping(source = "transactionDate", target = "createdAt", qualifiedBy = ToOffsetDateTime.class)
  @Mapping(source = "ownerId", target = "ownerId")
  TransactionResponse toDto(Transaction domain);


  @Mapping(source = "id", target = "id")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "transactionDate", target = "transactionDate", qualifiedBy = ToOffsetDateTime.class)
  @Mapping(source = "transferDetails", target = "transferDetails")
  @Mapping(source = "ownerId", target = "ownerId")
  TransferResponse toTransferDto(Transaction domain);


  @Mapping(source = "id", target = "id")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "accountId", target = "accountId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(source = "transactionDate", target = "createdAt", qualifiedBy = ToOffsetDateTime.class)
  @Mapping(source = "transferDetails", target = "transferDetails", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(source = "type", target = "transactionType", qualifiedByName = "stringToTransactionTypeEnum")
  @Mapping(source = "ownerId", target = "ownerId")
  TransactionResponse toDtoWithTransfer(Transaction domain);

  @ToOffsetDateTime
  default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
    return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
  }


  @Named("stringToTransactionTypeEnum")
  default TransactionTypeEnum stringToTransactionTypeEnum(String type) {
    try {
      return TransactionTypeEnum.valueOf(type.toUpperCase());
    } catch (Exception e) {
      throw new IllegalArgumentException("No enum constant for type: " + type);
    }
  }


  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface ToOffsetDateTime {

  }
}
