package africa.semicolon.ecommerce.data.model;

import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
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
    @OneToMany
    private Map<Long, Item> items = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;




    public void addItem(Product product, int quantity) throws ProductNotFoundException {
        Long productId = product.getId();
        Item item = new Item();
        System.out.println("total of cart to before add: "+total);

        item.setProduct(product);
        System.out.println("price of product to be added: "+item.getProduct().getPrice());
////        if (item.getQuantity() > product.getQuantity()){
////            throw new  IllegalArgumentException("jsdhds");
////        }
        if (items.containsKey(productId)) {
            items.get(productId).increaseQuantity(quantity);
           // calculateTotal( product, quantity);
          //  total = total.multiply()
        } else {
////            items.putIfAbsent(productId, item);
            items.put(productId, item);
            item.increaseQuantity(quantity);


        }
        total = total.add( calculateTotal( product,  quantity));

        System.out.println(total);
    }

    private BigDecimal calculateTotal(Product product, int quantity) {
        BigDecimal total = BigDecimal.ZERO;
        System.out.println("items in cart--> "+items.values().size());
        for (int i = 0; i < quantity; i++) {
            total = total.add(product.getPrice());
        }


////        for (Map.Entry<String, Item> item : items.entrySet()){
////            total = total.add(item.getValue().getProduct().getPrice().multiply(new BigDecimal(item.getValue().getQuantity())));
////           // total = total.multiply(new BigDecimal( item.getValue().getQuantity()));
////            System.out.println("total of product  added in loop: "+total);
////        }
        this.total = this.total.add(total);
////        for (Item itemInCart : items.values()) {
////            System.out.println("item-> "+ itemInCart.getQuantity());
////            total = total.add(itemInCart.getProduct().getPrice());
////        }
        return total;
    }


    public void removeItem(Long productId, int quantity) throws CartException {
        if (items.containsKey(productId)) {
            items.get(productId).decreaseQuantity(quantity);
        } else {
            throw new CartException("Item not in cart");
        }
       total = total.subtract( calculateTotal( items.get(productId).getProduct(),  quantity));
    }


    public void removeItem(Long productId) {
       total = total.subtract( calculateTotal( items.get(productId).getProduct(),
               items.get(productId).getQuantity()));

        items.remove(productId);

//       // calculateTotal();
    }



}
