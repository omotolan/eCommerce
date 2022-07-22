package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
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
    private List<Review> reviews = new ArrayList<>();


    public static Product unPackDto(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategoryName(productDto.getCategoryName());
        product.setQuantity(productDto.getQuantity());

        return product;
    }

    public static ProductDto packDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setCategoryName(product.getCategoryName());
        productDto.setReviews(product.getReviews());
        productDto.setQuantity(product.getQuantity());

        return productDto;
    }

}
