package nttd.bootcamp.microservices.creditcardservice.domain.model.constants;

public class CreditCardConstant {

  public static final String CURRENT_TASK_NOT_ALLOW_TO_DELETE = "Credit card with ID %s not found";

  public static final String CURRENT_OPERATION_FAILED = "There was a problem deleting the credit card %s";

  public static final String CURRENT_INSUFFICIENT_CREDIT = "Insufficient credit";
  public static final String CURRENT_CREDIT_CARD_NOT_FOUND = "Credit card not found - ID %s";

  public static final String OVERDUE_DEBT_ERROR_MESSAGE = "Customers with overdue debts cannot purchase new products.";
}
