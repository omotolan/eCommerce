package africa.semicolon.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateProductDto {
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
    private String image;
    @NotNull
    @NotBlank(message = "Please enter a category id")
    private String categoryName;
    @Min(value = 1, message = "quantity can not be zero")
    private int quantity;
}
