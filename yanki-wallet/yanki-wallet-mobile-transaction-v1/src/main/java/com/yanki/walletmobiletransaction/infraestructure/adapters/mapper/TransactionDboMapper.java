package com.yanki.walletmobiletransaction.infraestructure.adapters.mapper;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.infraestructure.adapters.entity.TransactionEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionDboMapper {


  @Mapping(source = "id", target = "id")
  TransactionEntity toDboStatus(Transaction transaction);


  @Mapping(source = "id", target = "id")
  @Mapping(source = "type", target = "type")
  @Mapping(source = "amount", target = "amount")
  @Mapping(source = "currency", target = "currency")
  @Mapping(source = "timestamp", target = "timestamp")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "sender", target = "sender")
  @Mapping(source = "receiver", target = "receiver")
  @Mapping(source = "metadata", target = "metadata")
  @Mapping(source = "auditInfo", target = "auditInfo")
  TransactionEntity toEntity(Transaction transaction);

  @InheritConfiguration
  Transaction toDomain(TransactionEntity transactionEntity);



  @Mapping(source = "sender.walletId", target = "sender.walletId")
  @Mapping(source = "debitCard.bankAccountId", target = "debitCard.bankAccountId")
  @Mapping(source = "debitCard.debitCardId", target = "debitCard.debitCardId")
  TransactionEntity toDboLinkDebit(Transaction transaction);
}
