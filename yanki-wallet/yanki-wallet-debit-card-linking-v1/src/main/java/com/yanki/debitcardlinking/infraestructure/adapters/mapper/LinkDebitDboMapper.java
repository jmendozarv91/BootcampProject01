package com.yanki.debitcardlinking.infraestructure.adapters.mapper;

import com.yanki.debitcardlinking.domain.model.LinkDebitCard;
import com.yanki.debitcardlinking.infraestructure.adapters.entity.LinkDebitCardEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkDebitDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "cardNumber", target = "cardNumber")
  @Mapping(source = "bankId", target = "bankId")
  @Mapping(source = "wallet.id", target = "walletId")
  @Mapping(source = "associatedAt", target = "associatedAt")
  @Mapping(source = "active", target = "active")
  LinkDebitCardEntity toDbo(LinkDebitCard model);

  @InheritConfiguration
  LinkDebitCard toDomain(LinkDebitCardEntity entity);
}
