package com.yanki.wallet.user.registration.userregistration.application.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEventContainer implements Serializable {
  private TransactionEvent transactionEvent;
  @JsonCreator
  public TransactionEventContainer(@JsonProperty("transactionEvent") String transactionEvent)
      throws JsonProcessingException {
    // Deserializa el JSON en un objeto TransactionEvent
    ObjectMapper objectMapper = new ObjectMapper();
    this.transactionEvent = objectMapper.readValue(transactionEvent, TransactionEvent.class);
  }
}
