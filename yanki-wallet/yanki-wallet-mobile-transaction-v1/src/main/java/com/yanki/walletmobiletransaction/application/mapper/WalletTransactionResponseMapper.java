package com.yanki.walletmobiletransaction.application.mapper;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface WalletTransactionResponseMapper {

  @Mapping(source = "id", target = "transactionId")
  @Mapping(source = "sender.walletId", target = "sourceWalletId")
  @Mapping(source = "receiver.walletId", target = "targetWalletId")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "type", target = "status")
  @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "dateToOffsetDateTime")
  TransactionResponse toDto(Transaction domain);

  @Named("dateToOffsetDateTime")
  default OffsetDateTime dateToOffsetDateTime(Date date) {
    if (date!=null){
      return date.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
    return null;
  }

  default TransactionResponse toTransactionResponse(Transaction domain) {
    if (domain == null) {
      return null;
    }
    TransactionResponse response = new TransactionResponse();
    response.setTransactionId(domain.getId());
    response.setSourceWalletId(
        domain.getSender() != null ? domain.getSender().getWalletId() : null);
    response.setTargetWalletId(
        domain.getReceiver() != null ? domain.getReceiver().getWalletId() : null);
    response.setAmount(domain.getAmount());
    response.setStatus(domain.getStatus());
    if (domain.getTimestamp() != null) {
      response.setTimestamp(
          domain.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime());
    }
    return response;
  }

}
