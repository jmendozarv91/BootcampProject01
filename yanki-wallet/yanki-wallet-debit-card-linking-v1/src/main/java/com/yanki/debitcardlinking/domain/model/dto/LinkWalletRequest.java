package com.yanki.debitcardlinking.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkWalletRequest {
  @JsonProperty("walletId")
  private String walletId;

  @JsonProperty("accountId")
  private String accountId;

}
