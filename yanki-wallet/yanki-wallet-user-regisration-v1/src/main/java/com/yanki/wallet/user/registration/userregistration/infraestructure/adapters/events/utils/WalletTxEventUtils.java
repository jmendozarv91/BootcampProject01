package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanki.wallet.user.registration.userregistration.application.event.TransactionEvent;

public class WalletTxEventUtils {
  public static TransactionEvent fromJson(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, TransactionEvent.class);
  }
}
