package nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
  DEPOSIT("deposit"),
  WITHDRAWAL("withdrawal"),
  PAYMENT("payment"),
  CHARGE("charger"),
  TRANSFER("transfer");

  private final String code;

  TransactionType(String code) {
    this.code = code;
  }

  public static TransactionType fromCode(String code) {
    for (TransactionType transactionType : values()) {
      if (transactionType.getCode().equals(code)) {
        return transactionType;
      }
    }
    throw new IllegalArgumentException("Tipo de transacción no válido: " + code);
  }

}
