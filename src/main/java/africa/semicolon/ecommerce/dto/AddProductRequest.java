package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class AddProductRequest {
    @NotNull
    @NotBlank(message = "Please enter a product name")
    private String name;
    @NotNull
    @NotBlank(message = "Please enter a product price")
    @Positive
    private BigDecimal price;
    @NotNull
    @NotBlank(message = "Please enter a product description")
    private String description;
    private String imageUrl;
    @Min(value = 1, message = "quantity should be greater than zero")
    private int quantity;
    private List<String> categoryNames = new ArrayList<>();
}
