package com.yanki.walletmobiletransaction.infraestructure.adapters.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "wallet_transactions")
public class TransactionEntity {

  private String id;
  private String type;
  private Double amount;
  private String currency;
  private Date timestamp;
  private String status;
  private ParticipantEntity sender;
  private ParticipantEntity receiver;
  private MetadataEntity metadata;
  private AuditInfoEntity auditInfo;
  private DebitCardEntity debitCard;


  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class ParticipantEntity {

    private String userId;
    private String phoneNumber;
    private String walletId;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class MetadataEntity {

    private String message;
    private DeviceInfoEntity deviceInfo;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class DeviceInfoEntity {

    private String imei;
    private String deviceModel;
    private LocationEntity location;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class LocationEntity {

    private Double lat;
    private Double lng;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class AuditInfoEntity {

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
  public static class DebitCardEntity {
    private String userId;
    private String debitCardId;
    private String bankAccountId;
  }



}
