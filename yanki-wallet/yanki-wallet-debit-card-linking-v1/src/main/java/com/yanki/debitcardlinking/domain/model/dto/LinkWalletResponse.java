package com.yanki.debitcardlinking.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkWalletResponse {
  @JsonProperty("cardNumber")
  private String cardNumber;

  @JsonProperty("walletId")
  private String walletId;
}
