package nttd.bootcamp.microservices.accountmanagement.domain.model.enums;

import lombok.Getter;

@Getter
public enum ProfileType {
  VIP("vip"),
  PYME("pyme");

  private final String code;

  ProfileType(String code) {
    this.code = code;
  }

  public static ProfileType fromCode(String code) {
    for (ProfileType profileType : values()) {
      if (profileType.getCode().equals(code)) {
        return profileType;
      }
    }
    throw new IllegalArgumentException("Unknown profile: " + code);
  }
}