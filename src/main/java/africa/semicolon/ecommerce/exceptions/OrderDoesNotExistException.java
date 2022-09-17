package africa.semicolon.ecommerce.exceptions;

public class OrderDoesNotExistException extends EcommerceException{
    public OrderDoesNotExistException(String message) {
        super(message);
    }
}
