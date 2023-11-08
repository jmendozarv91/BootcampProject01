package com.yanki.wallet.user.registration.userregistration.application.mapper;


import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.NewUserRequest;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationRequestMapper {


  @Mapping(source = "documentType", target = "personalInfo.documentType")
  @Mapping(source = "documentNumber", target = "personalInfo.documentNumber")
  @Mapping(source = "phoneNumber", target = "personalInfo.phoneNumber")
  @Mapping(source = "imei", target = "personalInfo.imei")
  @Mapping(source = "email", target = "personalInfo.email")
  @Mapping(source = "password", target = "security.passwordHash")
  @Mapping(source = "securityQuestions", target = "security.securityQuestions")
  User toDomain(NewUserRequest request);

  User toDomainUpdateUser(UpdateUserRequest request);

  User.PersonalInfo  toDomainSettings(UpdateUserRequest request);


}
