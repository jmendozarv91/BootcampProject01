package com.yanki.wallet.user.registration.userregistration.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yanki.wallet.user.registration.userregistration.application.event.enums.WalletTxEventType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletTxEvent implements Serializable {

  private String transactionId;
  private String sourceWalletId;
  private String targetWalletId;
  private String targetPhoneNumber;
  private double amount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String status;
  private String debitCardNumber;
  private String bankAccountId;

//  public enum Status {
//    CREATED,
//    PENDING,
//    COMPLETED,
//    CANCELED
//  }
}
