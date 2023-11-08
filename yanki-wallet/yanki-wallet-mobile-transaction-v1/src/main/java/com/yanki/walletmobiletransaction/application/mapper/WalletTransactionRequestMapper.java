package com.yanki.walletmobiletransaction.application.mapper;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletTransactionRequestMapper {
  @Mapping(source = "sourceWalletId", target = "sender.walletId")
  @Mapping(source = "targetWalletId", target = "receiver.walletId")
  @Mapping(source = "amount", target = "amount")
  Transaction toDomain(TransactionRequest request);
}
