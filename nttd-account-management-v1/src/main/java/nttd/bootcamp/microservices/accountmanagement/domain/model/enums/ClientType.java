package nttd.bootcamp.microservices.accountmanagement.domain.model.enums;


import lombok.Getter;

@Getter
public enum ClientType {

  PERSONAL("01", "Cliente Personal"),
  BUSINESS("02", "Cliente Empresarial");

  private final String code;
  private final String description;

  ClientType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public static ClientType fromCode(String code) {
    for (ClientType type : ClientType.values()) {
      if (type.getCode().equals(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No ClientType with code " + code + " found");
  }
}