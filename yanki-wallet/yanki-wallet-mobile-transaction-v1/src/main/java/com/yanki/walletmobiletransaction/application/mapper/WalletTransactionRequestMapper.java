package com.yanki.walletmobiletransaction.application.mapper;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletTransactionRequestMapper {
  @Mapping(source = "sourceWalletId", target = "sender.walletId")
  @Mapping(source = "targetWalletId", target = "receiver.walletId")
  @Mapping(source = "amount", target = "amount")
  Transaction toDomain(TransactionRequest request);


  @Mapping(source = "sourceWalletId", target = "sender.walletId")
  @Mapping(source = "targetPhoneNumber", target = "receiver.phoneNumber")
  @Mapping(source = "amount", target = "amount")
  Transaction walletTransferRequestToDomain(WalletTransferRequest request);


  @Mapping(source = "sender.walletId", target = "sourceWalletId")
  @Mapping(source = "receiver.walletId", target = "targetWalletId")
  @Mapping(source = "amount", target = "amount")
  TransactionRequest toCancelTransactionRequest(Transaction transaction);
}
