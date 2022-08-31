package africa.semicolon.ecommerce.exceptions;

public class ProductNotFoundException extends EcommerceException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
