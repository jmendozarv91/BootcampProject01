package com.yanki.wallet.user.registration.userregistration.application.mapper;

import com.yanki.wallet.user.registration.userregistration.domain.model.User;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.UserResponse;
import com.yanki.wallet.user.registration.userregistration.domain.model.dto.WalletResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationResponseMapper {


  @Mapping(target = "documentType", source = "personalInfo.documentType")
  @Mapping(target = "documentNumber", source = "personalInfo.documentNumber")
  @Mapping(target = "phoneNumber", source = "personalInfo.phoneNumber")
  @Mapping(target = "imei", source = "personalInfo.imei")
  @Mapping(target = "email", source = "personalInfo.email")
  UserResponse toUserResponse(User model);



  @Mapping(source = "id", target = "userId")
  @Mapping(source = "wallet.balance", target = "balance")
  @Mapping(source = "wallet.transactions", target = "transactions")
  WalletResponse toWallerResponse(User model);

}
