package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("products")
public class Product {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
    private int quantity;
    private LocalDateTime dateCreated = LocalDateTime.now();
    @DBRef
    private List<Review> reviews = new ArrayList<>();
    private String categoryName;

}
