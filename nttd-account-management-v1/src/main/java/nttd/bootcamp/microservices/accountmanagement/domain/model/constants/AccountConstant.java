package nttd.bootcamp.microservices.accountmanagement.domain.model.constants;

public class AccountConstant {


  public static final Double MOUNT_MINIMUM_OPENING_AMOUNT_VIP = 500.0;
  public static final String MINIMUM_OPENING_AMOUNT_VIP = "El monto mínimo de apertura para una cuenta VIP es de %s.";
  // ... otras constantes
  public static final String CURRENT_TASK_NOT_ALLOW_TO_DELETE = "Account with ID %s not found";
  public static final String CURRENT_OPERATION_FAILED = "There was a problem deleting the account %s";

  public static final String MAX_SPECIFIED_ACCOUNTS = "Client can't have more than 3 specified accounts";

  public static final String ACCOUNT_REGISTRATION_ERROR = "The customer cannot register the account";

  public static final String ACCOUNT_UNEXPECTED_ERROR = "Unexpected error";

  public static final String BUSINESS_CLIENT_ACCOUNT_ERROR = "The business client cannot have savings or fixed-term accounts";

  public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found";

  public static final String INITIAL_AMOUNT_LESS_THAN_MINIMUM = "Initial amount is less than the minimum opening amount.";

  public static final String INSUFFICIENT_FUNDS = "Insufficient funds";

  public static final String INVALID_TRANSFER_BETWEEN_DIFFERENT_BANKS = "Invalid transfer between accounts from different banks";

  public static final String VIP_CLIENT_MUST_HAVE_CREDIT_CARD = "The VIP client must have a credit card with the bank";
  public static final String PYME_CLIENTS_MUST_HAVE_CURRENT_ACCOUNT = "PYME clients must have a current account.";

  public static final String PYME_CLIENTS_MUST_HAVE_CREDIT_CARD = "PYME clients must have a credit card.";


}
