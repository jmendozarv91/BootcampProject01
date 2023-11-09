package com.yanki.walletmobiletransaction.domain.model.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
  CREATE("create");

  private final String code;

  TransactionStatus(String code) {
    this.code = code;
  }

  public static TransactionStatus fromCode(String code) {
    for (TransactionStatus type : TransactionStatus.values()) {
      if (type.getCode().equals(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No TransactionStatus with code " + code + " found");
  }
}
