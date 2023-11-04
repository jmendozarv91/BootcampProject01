package nttd.bootcamp.microservices.inquiryreport.inquiryservices.application.mapper;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

//  @Mapping(target = "type", constant = "ACCOUNT")
//  @Mapping(target = "details.account", expression = "java(java.util.Collections.singletonList(accountTransaction))")
//  Transaction toAccountTransaction(AccountTransaction accountTransaction);
//
//  @Mapping(target = "type", constant = "CREDITCARD")
//  @Mapping(target = "details.creditCard", expression = "java(java.util.Collections.singletonList(creditCardTransaction))")
//  Transaction toCreditCardTransaction(CreditCardTransaction creditCardTransaction);
//
//  @Mapping(target = "type", constant = "CREDIT")
//  @Mapping(target = "details.credit", expression = "java(java.util.Collections.singletonList(creditTransaction))")
//  Transaction toCreditTransaction(CreditTransaction creditTransaction);
}
