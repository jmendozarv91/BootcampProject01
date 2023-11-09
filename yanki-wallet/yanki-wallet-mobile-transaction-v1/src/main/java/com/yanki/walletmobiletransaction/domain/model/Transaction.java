package com.yanki.walletmobiletransaction.domain.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {

  private String id;
  private String type;
  private Double amount;
  private String currency;
  private Date timestamp;
  private String status;
  private Participant sender;
  private Participant receiver;
  private Metadata metadata;
  private AuditInfo auditInfo;
  private DebitCard debitCard;


  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Participant {

    private String userId;
    private String phoneNumber;
    private String walletId;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Metadata {

    private String message;
    private DeviceInfo deviceInfo;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class DeviceInfo {

    private String imei;
    private String deviceModel;
    private Location location;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Location {

    private Double lat;
    private Double lng;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class AuditInfo {

    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class DebitCard {
    private String debitCardId;
    private String bankAccountId;
  }



}
