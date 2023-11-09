package com.yanki.wallet.user.registration.userregistration.application.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxEvent {
  private String transactionId;
  private String sourceWalletId;
  private String targetWalletId;
  private double amount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String status;
  private String debitCardNumber;
  private String bankAccountId;

  public enum Status {
    CREATED,
    PENDING,
    COMPLETED,
    CANCELED
  }
}
