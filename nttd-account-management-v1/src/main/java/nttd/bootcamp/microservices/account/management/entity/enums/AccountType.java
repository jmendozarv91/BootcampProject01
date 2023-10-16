package nttd.bootcamp.microservices.account.management.entity.enums;

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
}
