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
@Builder
public class Item {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @OneToOne
    private Product product;
    private int quantity;



        public Item validateItemQuantity(Product product, int quantity){

            if (quantity > product.getQuantity() && quantity <= 0){
                throw new IllegalArgumentException("Invalid quantity");
            }
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(quantity);
            return item;
        }
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;

    }


    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
//        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
