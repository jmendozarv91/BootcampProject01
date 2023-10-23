package nttd.bootcamp.microservices.accountmanagement.application.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Account;
import nttd.bootcamp.microservices.accountmanagement.domain.model.Transaction;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.AccountResponse;
import nttd.bootcamp.microservices.accountmanagement.domain.model.dto.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {

  /**
   * Converts an {@link Account} domain object to an {@link AccountResponse} DTO.
   *
   * @param domain the account domain object to convert, must not be null.
   * @return the converted account DTO.
   */
  @Mapping(source = "id", target = "id")
  @Mapping(source = "accountNumber", target = "accountNumber")
  @Mapping(source = "accountType", target = "accountType")
  @Mapping(source = "balance", target = "balance")
  @Mapping(source = "maxMonthlyTransactions", target = "maxMonthlyTransactions")
  @Mapping(source = "maintenanceFee", target = "maintenanceFee")
  @Mapping(source = "ownerId", target = "ownerId")
  @Mapping(source = "minimumOpeningAmount", target = "minimumOpeningAmount")
  AccountResponse toDto(Account domain);


  /**
   * Converts a {@link Transaction} domain object to a {@link TransactionResponse} DTO.
   *
   * @param transaction the transaction domain object to convert, must not be null.
   * @return the converted transaction DTO.
   */
  @Mapping(source = "id", target = "transactionId")
  @Mapping(source = "fromAccountId", target = "fromAccountId")
  @Mapping(source = "toAccountId", target = "toAccountId")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "transactionDate", target = "transactionDate",qualifiedBy =ToOffsetDateTime.class)
  TransactionResponse toDtoTransaction(Transaction transaction);


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
