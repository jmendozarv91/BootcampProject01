package com.yanki.debitcardlinking.infraestructure.adapters.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wallets")
public class WalletEntity {
  @Id
  private String id;
  private String userId;
  private double balance;
}
