package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.mapper;


import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.entity.UserEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRegistrationDboMapper {



  @Mapping(source = "id", target = "id")
  @Mapping(source = "personalInfo", target = "personalInfo")
  @Mapping(source = "wallet", target = "wallet")
  @Mapping(source = "security", target = "security")
  @Mapping(source = "settings.currency", target = "settings.currency")
  @Mapping(source = "settings.language", target = "settings.language")
  UserEntity toDbo(User request);

  @InheritConfiguration
  User toDomain(UserEntity entity);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "wallet",target = "wallet")
  @Mapping(source = "personalInfo.email",target = "personalInfo.email")
  UserEntity updateDbo(User model,@MappingTarget UserEntity userEntity);
}
