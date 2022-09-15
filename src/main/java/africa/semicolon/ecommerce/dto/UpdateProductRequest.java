package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Validated
public class UpdateProductRequest {
    @NotNull
    @NotBlank(message = "Please enter a product name")
    private String name;
    @NotNull
    @NotBlank(message = "Please enter a product price")
    private BigDecimal price;
    @NotNull
    @NotBlank(message = "Please enter a product description")
    private String description;
    @NotNull
    @NotBlank(message = "Please enter a product image")
    private String imageUrl;
    @NotNull
    @NotBlank(message = "Please enter a category id")
    private List<ProductCategory> productCategory;
    @Min(value = 1, message = "quantity can not be zero")
    private int quantity;
}
