package com.yanki.walletmobiletransaction.application.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionEvent implements Serializable {

  private String transactionId;
  private EventType eventType;
  private String status;
  private String sourceWalletId;
  private String targetWalletId;
  private String debitCardId;
  private String targetPhoneNumber;
  private double amount;
  private String debitCardNumber;
  private String bankAccountId;

  public enum EventType {
    CANCEL_TRANSACTION,
    UPDATE_TRANSACTION_STATUS,
    TRANSFER_WALLET_TO_WALLET,
    TRANSACTION_CANCELED, TRANSACTION_STATUS_UPDATED, WALLET_TO_WALLET_TRANSFER, DEBIT_CARD_LINKED,
    LOAD_WALLET_FROM_DEBIT_CARD, CREDIT_WALLET_TO_BANK_ACCOUNT, LINK_DEBIT_CARD
  }
}
