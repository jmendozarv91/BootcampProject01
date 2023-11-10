package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.mapper;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.entity.DebitCardEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DebitCardDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "cardNumber", target = "cardNumber")
  @Mapping(source = "expirationDate", target = "expirationDate")
  @Mapping(source = "securityCode", target = "securityCode")
  @Mapping(source = "balance", target = "balance")
  @Mapping(source = "primaryAccountId", target = "primaryAccountId")
  @Mapping(source = "associatedAccountIds", target = "associatedAccountIds")
  @Mapping(source = "walletId", target = "walletId")
  DebitCardEntity toDo(DebitCard debitCard);

  @InheritConfiguration
  DebitCard toDomain(DebitCardEntity debitCardEntity);
}
