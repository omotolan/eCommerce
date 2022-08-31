package africa.semicolon.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductResponse extends Response {
    private ProductDto productDto;

    public AddProductResponse(ProductDto productDto,String message) {
        super(message);
        this.productDto = productDto;
    }
}
