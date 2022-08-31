package africa.semicolon.ecommerce.data.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Item {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @OneToOne
    private Product product;
    private int quantity;


    public void increaseQuantity(int quantity) {
        this.quantity += quantity;

    }


    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
//        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
