package africa.semicolon.ecommerce.dto.responses;

import africa.semicolon.ecommerce.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private String message;
    private Product savedProduct;

    @Override
    public String toString(){
        return message;
    }
}
