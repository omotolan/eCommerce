package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("reviews")
public class Review {
    @Id
    private String id;
    private String productId;
    private String review;
    private LocalDateTime dateCreated = LocalDateTime.now();

}
