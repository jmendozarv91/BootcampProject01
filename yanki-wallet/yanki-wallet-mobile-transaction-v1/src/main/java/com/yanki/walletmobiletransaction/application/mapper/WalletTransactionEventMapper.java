package com.yanki.walletmobiletransaction.application.mapper;

import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletTransactionEventMapper {

  @Mapping(source = "id", target = "transactionId")
  @Mapping(source = "sender.walletId", target = "sourceWalletId")
  @Mapping(source = "receiver.walletId", target = "targetWalletId")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "type", target = "status")
  TransactionEvent toEvent(Transaction model);


  @Mapping(source = "id", target = "transactionId")
  @Mapping(source = "debitCard.debitCardNumber", target = "debitCardNumber")
  @Mapping(source = "debitCard.debitCardId", target = "debitCardId")
  @Mapping(source = "sender.walletId", target = "sourceWalletId")
  @Mapping(source = "debitCard.bankAccountId", target = "bankAccountId")
  @Mapping(source = "type", target = "eventType")
  TransactionEvent mapToDebitCardLinkedEvent(Transaction model);
}
