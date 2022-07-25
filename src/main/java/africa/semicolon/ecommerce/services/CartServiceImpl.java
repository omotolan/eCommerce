package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.Item;
import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.repositories.CartRepository;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.ProductException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Override
    public void createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
    }

    private Item findItemInCart(String productName, String cartId) throws ProductException, CartException {
        Product product1 = productService.findProductByName(productName);
        Item item = new Item();
        Cart cart = findCartByTheId(cartId);
        for (Item item1 : cart.getItems().values()) {
            if (item1.getProduct().getName().equals(product1.getName())) {
                item = item1;
                return item;
            }
        }

        return item;
    }

    @Override
    public Response addItemToCart(String cartId, Item item) throws ProductException, CartException {

        Product product = productService.findProduct(item.getProduct().getId());

        Cart cart = findCartByTheId(cartId);
        cart.addItem(product, item.getQuantity());
        Item itemInDb = findItemInCart(product.getName(), cartId);
        int number = itemInDb.getQuantity() + item.getQuantity();
        // TODO: find right position for this exception
        if (number > product.getQuantity()) {
            throw new ProductException("exceeds quantity");
        }

        cartRepository.save(cart);
        return new Response(item.getProduct().getName() + " added to cart");

    }

    private Cart findCartByTheId(String cartId) throws CartException {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new CartException("cart not found");
        }
        return cart.get();
    }

    @Override
    public BigDecimal getCartTotal(String cartId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        return cart.getTotal();
    }

    @Override
    public Response removeItemFromCart(String cartId, Item item) throws ProductException, CartException {
        Cart cart = findCartByTheId(cartId);
        Product product = productService.findProduct(item.getProduct().getId());
        cart.removeItem(product.getId());

        cartRepository.save(cart);
        return new Response(product.getName() + " removed from cart");
    }

    @Override
    public Response reduceItemQuantityInCart(String cartId, Item item, int quantity) throws CartException, ProductException {
        Cart cart = findCartByTheId(cartId);
        Product product = productService.findProduct(item.getProduct().getId());
        Response response = new Response();
        Item itemFound = findItemInCart(product.getName(), cartId);

         cart.removeItem(product.getId(), quantity);

        // TODO: work on the removal if quantity equals 0
        if (itemFound.getQuantity() < 1 || itemFound.getQuantity() < quantity ) {
            cart.removeItem(product.getId());
            response.setMessage(item.getProduct().getName() + " removed");
        }
        cartRepository.save(cart);

        return response;
    }

    @Override
    public Response clearCart(String cartId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        // TODO: fix
        for (Item item : cart.getItems().values()) {
            cart.removeItem(item.getProduct().getId());
        }
        cartRepository.save(cart);
        return new Response("cart cleared");
    }
}
