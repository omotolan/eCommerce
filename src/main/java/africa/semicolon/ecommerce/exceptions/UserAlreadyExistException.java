package africa.semicolon.ecommerce.exceptions;

public class UserAlreadyExistException extends EcommerceException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
