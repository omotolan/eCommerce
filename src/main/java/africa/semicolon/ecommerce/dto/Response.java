package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Product;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@AllArgsConstructor
public class Response {
    private String message;


    @Override
    public String toString(){
        return message;
    }
}
