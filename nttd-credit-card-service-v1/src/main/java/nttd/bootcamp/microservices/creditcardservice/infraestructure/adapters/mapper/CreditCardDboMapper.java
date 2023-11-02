package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.mapper;

import nttd.bootcamp.microservices.creditcardservice.domain.model.CreditCard;
import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.entities.CreditCardEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "cardId", target = "cardId")
  @Mapping(source = "clientId", target = "clientId")
  @Mapping(source = "creditLimit", target = "creditLimit")
  @Mapping(source = "availableBalance", target = "availableBalance")
  @Mapping(source = "dueDate", target = "dueDate")
  CreditCardEntity toDbo(CreditCard domain);


  @InheritConfiguration
  CreditCard toDomain(CreditCardEntity entity);


}
