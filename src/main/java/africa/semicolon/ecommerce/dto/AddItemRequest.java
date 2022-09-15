package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Product;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class AddItemRequest {
    private Product product;
    @Positive
    private int quantity;
}
