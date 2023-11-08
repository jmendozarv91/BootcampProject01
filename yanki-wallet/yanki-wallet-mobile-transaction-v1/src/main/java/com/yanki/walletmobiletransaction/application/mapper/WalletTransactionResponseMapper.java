package com.yanki.walletmobiletransaction.application.mapper;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletTransactionResponseMapper {
  @Mapping(source = "id", target = "transactionId")
  @Mapping(source = "sender.walletId", target = "sourceWalletId")
  @Mapping(source = "receiver.walletId", target = "targetWalletId")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "timestamp", target = "timestamp")
  @Mapping(source = "timestamp", target = "timestamp")
  TransactionResponse toDto(Transaction domain);

}
