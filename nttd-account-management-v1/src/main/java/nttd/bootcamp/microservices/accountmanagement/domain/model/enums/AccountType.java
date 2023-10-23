package nttd.bootcamp.microservices.accountmanagement.domain.model.enums;

import lombok.Getter;

@Getter
public enum AccountType {

  SAVING_ACCOUNT("001","saving account") ,
  CURRENT_ACCOUNT("002","current account") ,
  FIXED_TERM("003","fixed term") ;

  private final String code;
  private final String description;

  AccountType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public static AccountType fromCode(String code) {
    for (AccountType type : AccountType.values()) {
      if (type.getCode().equals(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No AccountType with code " + code + " found");
  }
}