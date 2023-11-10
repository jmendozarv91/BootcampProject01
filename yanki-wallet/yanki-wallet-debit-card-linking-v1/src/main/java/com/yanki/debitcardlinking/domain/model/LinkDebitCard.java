package com.yanki.debitcardlinking.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkDebitCard {
  private String id;
  private String cardNumber;
  private String bankId;
  private Wallet wallet;
  private boolean isActive;
  private LocalDate associatedAt;

  // Métodos de dominio, por ejemplo, para asociar un monedero
  public void associateWallet(Wallet wallet) {
    if (wallet != null) {
      this.wallet = wallet;
      this.isActive = true;
      this.associatedAt = LocalDate.now();
    } else {
      throw new IllegalArgumentException("Wallet cannot be null");
    }
  }
}
