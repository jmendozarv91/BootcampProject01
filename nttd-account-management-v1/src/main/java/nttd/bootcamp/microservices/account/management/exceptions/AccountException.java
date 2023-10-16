package nttd.bootcamp.microservices.account.management.exceptions;

public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
    }
}
