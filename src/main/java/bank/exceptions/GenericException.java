package bank.exceptions;

public class GenericException extends RuntimeException {

  public String errorCode;

  public GenericException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
    this.errorCode = "BANK_INTERNAL_ERROR";
  }
}