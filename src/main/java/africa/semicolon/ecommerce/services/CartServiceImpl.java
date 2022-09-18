package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.Item;
import africa.semicolon.ecommerce.data.repositories.CartRepository;
import africa.semicolon.ecommerce.dto.requests.AddItemRequest;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.dto.requests.UpdateItemInCartRequest;
import africa.semicolon.ecommerce.exceptions.CartException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
    }

    @Override
    public String addToCart(Long cartId, AddItemRequest addItemRequest) throws CartException {


        Item item = Item.builder()
                .product(addItemRequest.getProduct())
                .quantity(addItemRequest.getQuantity())
                .build();

        Cart cart = findCartByTheId(cartId);
        cart.addItem(item);
        cartRepository.save(cart);

        return item.getProduct().getName() + " added to cart";
    }


    private Cart findCartByTheId(Long id) throws CartException {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new CartException("cart not found");
        }
        return cart.get();
    }

    @Override
    public Cart getCartById(Long cartId) throws CartException {
        return findCartByTheId(cartId);
    }

    @Override
    public BigDecimal getCartTotal(Long cartId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        return cart.getTotal();
    }

    @Override
    public Response removeItemFromCart(Long cartId, Long productId) throws CartException {
        Cart cart = findCartByTheId(cartId);
        cart.removeItem(productId);

        cartRepository.save(cart);
        return new Response("product removed from cart");
    }

    @Override
    public Response reduceItemQuantityInCart(Long cartId, UpdateItemInCartRequest updateItemInCartRequest) throws CartException {
        Cart cart = findCartByTheId(cartId);
        cart.reduceItemQuantity(updateItemInCartRequest);

        cartRepository.save(cart);
        Response response = new Response();
        response.setMessage(updateItemInCartRequest.getProduct().getName() + " updated");

        return response;
    }

    @Override
    public Response clearCart(Long cartId) throws CartException {

        Cart cart = findCartByTheId(cartId);

        cart.getItems().values().stream().map(item -> {
            cart.removeItem(item.getProduct().getId());
            return cart;
        });

        for (Item item : cart.getItems().values()) {
            cart.removeItem(item.getProduct().getId());
        }
        cartRepository.save(cart);
        return new Response("cart cleared");
    }
}
