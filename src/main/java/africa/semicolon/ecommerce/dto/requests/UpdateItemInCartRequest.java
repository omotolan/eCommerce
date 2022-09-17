package africa.semicolon.ecommerce.dto.requests;

import africa.semicolon.ecommerce.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UpdateItemInCartRequest {
    @NotNull
    private Product product;
    @Positive
    private int quantity;
}
