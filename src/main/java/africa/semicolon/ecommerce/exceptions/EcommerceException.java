package africa.semicolon.ecommerce.exceptions;

public class EcommerceException extends Exception{
    private final String message;

    public EcommerceException(String message){
        this.message = message;
    }
    @Override
    public String toString() {
        return "EcommerceException{" +
                "message='" + message + '\'' +
                '}';
    }
}
