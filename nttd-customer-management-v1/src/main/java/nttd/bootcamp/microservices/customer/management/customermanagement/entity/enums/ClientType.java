package nttd.bootcamp.microservices.customer.management.customermanagement.entity.enums;

import lombok.Getter;

@Getter
public enum ClientType {
    PERSONAL("01","personal"),
    BUSINESS("02","business");

    private final String code;
    private final String type;

    ClientType(String code, String type) {
        this.code = code;
        this.type = type;
    }
}
