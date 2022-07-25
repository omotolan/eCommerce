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
    private BigDecimal itemTotal = BigDecimal.ZERO;
    // TODO: clear initialization error

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
        calculateTotal();

    }
    private void calculateTotal() {
        BigDecimal total;
        total = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        this.itemTotal = total;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
        calculateTotal();
    }
}
