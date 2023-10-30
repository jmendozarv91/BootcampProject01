package nttd.bootcamp.microservices.debitcardmanagement.application.mapper;

import nttd.bootcamp.microservices.debitcardmanagement.domain.model.DebitCard;
import nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto.CreateDebitCardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DebitCardResponseMapper {
  @Mapping(source = "cardNumber", target = "cardNumber")
  CreateDebitCardResponse toDto(DebitCard domain);
}
