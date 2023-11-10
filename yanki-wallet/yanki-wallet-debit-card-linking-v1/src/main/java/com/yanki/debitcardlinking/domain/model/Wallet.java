package com.yanki.debitcardlinking.domain.model;

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
public class Wallet {
  private String id;
  private String userId;
  private double balance;

  // Constructor, getters y setters

  // Métodos de dominio, por ejemplo, para efectuar transacciones
  public void applyTransaction(double amount) {
    this.balance += amount; // Simplificado, no incluye validaciones
  }

}
