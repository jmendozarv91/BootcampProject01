package nttd.bootcamp.microservices.creditservice.entity;

import lombok.Getter;

@Getter
public enum ClientType {

  PERSONAL("01", "personal"),
  BUSINESS("02", "business");

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
