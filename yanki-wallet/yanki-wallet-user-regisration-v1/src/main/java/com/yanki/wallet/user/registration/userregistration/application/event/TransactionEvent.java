package com.yanki.wallet.user.registration.userregistration.application.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionEvent implements Serializable {

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("sourceWalletId")
  private String sourceWalletId;

  @JsonProperty("targetWalletId")
  private String targetWalletId;

  @JsonProperty("targetPhoneNumber")
  private String targetPhoneNumber;

  @JsonProperty("eventType")
  private EventType eventType;

  @JsonProperty("amount")
  private double amount;

  @JsonProperty("status")
  private String status;

  @JsonProperty("debitCardNumber")
  private String debitCardNumber;

  @JsonProperty("bankAccountId")
  private String bankAccountId;





  @JsonCreator
  public TransactionEvent(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TransactionEvent transactionEvent = objectMapper.readValue(json, TransactionEvent.class);
    this.transactionId = transactionEvent.getTransactionId();
    this.eventType = transactionEvent.getEventType();
    this.status = transactionEvent.getStatus();
    this.sourceWalletId = transactionEvent.getSourceWalletId();
    this.targetWalletId = transactionEvent.getTargetWalletId();
    this.targetPhoneNumber = transactionEvent.getTargetPhoneNumber();
    this.amount = transactionEvent.getAmount();
    this.debitCardNumber = transactionEvent.getDebitCardNumber();
    this.bankAccountId = transactionEvent.getBankAccountId();
  }

  public enum EventType {
    CANCEL_TRANSACTION,
    UPDATE_TRANSACTION_STATUS,
    TRANSFER_WALLET_TO_WALLET,
    TRANSACTION_CANCELED, TRANSACTION_STATUS_UPDATED, WALLET_TO_WALLET_TRANSFER, DEBIT_CARD_LINKED,
    LOAD_WALLET_FROM_DEBIT_CARD, CREDIT_WALLET_TO_BANK_ACCOUNT, LINK_DEBIT_CARD
  }
}
//  public enum Status {
//    CREATED,
//    PENDING,
//    COMPLETED,
//    CANCELED
//  }

