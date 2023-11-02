package nttd.bootcamp.microservices.accounttransaction.domain.model.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
  DEPOSIT("deposit"),
  TRANSFER("transfer"),
  WITHDRAWAL("withdrawal");
  private final String code;

  TransactionType(String code) {
    this.code = code;
  }

  public static TransactionType fromCode(String code) {
    for (TransactionType type : TransactionType.values()) {
      if (type.getCode().equals(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No AccountType with code " + code + " found");
  }

}
