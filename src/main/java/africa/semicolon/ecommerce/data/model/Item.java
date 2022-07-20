package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private Product product;
    private int quantity = 1;
    private BigDecimal itemTotal;

    public void increaseQuantity(int quantity){
        this.quantity += quantity;
        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
    public void decreaseQuantity(int quantity){
        this.quantity -= quantity;
        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
