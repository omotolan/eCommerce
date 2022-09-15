package africa.semicolon.ecommerce.exceptions;

public class UserDoesNotExistException extends EcommerceException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
