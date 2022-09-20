package africa.semicolon.ecommerce.data.model;

import africa.semicolon.ecommerce.dto.requests.UpdateItemInCartRequest;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Map<Long, Item> items = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;


    private void validateItemQuantity(Product product, int quantity) {
        if (quantity > product.getQuantity() || quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
    }

    public void addItem(Item item) {
        validateItemQuantity(item.getProduct(), item.getQuantity());

        if (items.containsKey(item.getProduct().getId())) {
            items.replace(item.getProduct().getId(), item);
        } else {
            items.put(item.getProduct().getId(), item);

        }
        calculateTotal();
    }

    public void calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

//        total = (BigDecimal) items.values().stream()
//                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        for (Item item : items.values()) {
            total = item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity()));
        }
        this.total = total;
    }

    public void reduceItemQuantity(UpdateItemInCartRequest updateItemInCartRequest) {
        validateItemQuantity(updateItemInCartRequest.getProduct(), updateItemInCartRequest.getQuantity());
        if (items.containsKey(updateItemInCartRequest.getProduct().getId())) {
            Item item = Item.builder()
                    .product(updateItemInCartRequest.getProduct())
                    .quantity(updateItemInCartRequest.getQuantity())
                    .build();
            items.replace(updateItemInCartRequest.getProduct().getId(), item);
            calculateTotal();
        }

    }


    public void removeItem(Long productId) {
        if (items.containsKey(productId)) {
            items.remove(productId);
            calculateTotal();
        }
    }


}
