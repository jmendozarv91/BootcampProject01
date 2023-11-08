package com.yanki.wallet.user.registration.userregistration.domain.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  @Id
  private String id;
  private PersonalInfo personalInfo;
  private Wallet wallet;
  private Security security;
  private Settings settings;


  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class PersonalInfo {
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private String imei;
    private String email;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Wallet {
    private String id;
    private Double balance;
    private List<Transaction> transactions;
    private String linkedDebitCardId;
    private String status;
    private Date createdAt;
    private Date updatedAt;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Security {
    private String passwordHash;
    private List<SecurityQuestion> securityQuestions;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class SecurityQuestion {
    private String question;
    private String answerHash;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Settings {
    private String language;
    private String currency;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Transaction {
    private String id;
  }
}
