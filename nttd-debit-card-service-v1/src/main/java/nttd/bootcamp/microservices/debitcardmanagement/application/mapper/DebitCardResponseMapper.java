package nttd.bootcamp.microservices.debitcardmanagement.application.mapper;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardResponse;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.LinkWalletResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DebitCardResponseMapper {
  @Mapping(source = "cardNumber", target = "cardNumber")
  CreateDebitCardResponse toDto(DebitCard domain);

  @Mapping(source = "cardNumber", target = "cardNumber")
  @Mapping(source = "walletId", target = "walletId")
  LinkWalletResponse toLinkWalletResponse(DebitCard updatedDebitCard);
}
