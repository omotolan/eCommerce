package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("orders")
public class Order {
    @Id
    private String id;
    private String deliveryAddress;
    private BigDecimal orderTotal;
    private String userId;

}
