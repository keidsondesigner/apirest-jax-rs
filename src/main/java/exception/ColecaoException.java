package exception;

public class ColecaoException extends Exception {

  public ColecaoException(String message) {
    super(message);
  }

  public ColecaoException(Throwable cause) {
    super(cause);
  }

  public ColecaoException(String message, Throwable cause) {
    super(message, cause);
  }

}
