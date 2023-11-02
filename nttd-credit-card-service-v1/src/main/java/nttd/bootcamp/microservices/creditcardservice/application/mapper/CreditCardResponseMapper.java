package nttd.bootcamp.microservices.creditcardservice.application.mapper;

import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardResponseMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "cardId", target = "cardId")
  @Mapping(source = "clientId", target = "clientId")
  @Mapping(source = "creditLimit", target = "creditLimit")
  @Mapping(source = "availableBalance", target = "availableBalance")
  CreditCardResponse toDto(CreditCard domain);
}
