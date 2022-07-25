package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.Item;
import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.repositories.CartRepository;
import africa.semicolon.ecommerce.dto.CartDto;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.ProductException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testThatCartCanBeCreated() {
        cartService.createCart();
        assertEquals(1, cartRepository.count());
    }


    @Test
    public void testThatItemCanBeAddedToCart() throws ProductException, CartException {

        Product product = new Product();
//        product.setName("Samsung s20");
        product.setName("Iphone 13");

//        product.setId("62d9e3caa2755e3e4c948864");
        product.setId("62dc54cdeb40e53ec53cc160");

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(2);
        String cartId = "62dcf10090786e5bdafd7e89";
        Response response = cartService.addItemToCart(cartId, item);
        assertEquals("Samsung s20 added to cart", response.toString());
//        TODO: augend error with item total(when not initialized)
    }

    @Test
    public void testToGetCartTotal() throws CartException {
        String cartId = "62db9e0dfd89233479caa40e";
        BigDecimal total = cartService.getCartTotal(cartId);
        assertEquals(new BigDecimal(100000), total);
    }

    @Test
    public void testThatItemCanBeRemovedFromCart() throws ProductException, CartException {
        Product product = new Product();
        product.setName("Samsung s20");
        product.setId("62d9e3caa2755e3e4c948864");
        Item item = new Item();
        item.setProduct(product);
        String cartId = "62dcf10090786e5bdafd7e89";

        Response response = cartService.removeItemFromCart(cartId, item);
        assertEquals("Samsung s20 removed from cart", response.toString());
    }

    @Test
    public void testToReduceQuantityOfAnItem() throws CartException, ProductException {
        Product product = new Product();
        product.setName("Samsung s20");
        product.setId("62d9e3caa2755e3e4c948864");
        Item item = new Item();
        item.setProduct(product);
        String cartId = "62dcf10090786e5bdafd7e89";
        int quantity = 1;
        Response response = cartService.reduceItemQuantityInCart(cartId, item, quantity);
        assertEquals("", response.toString());

    }

    @Test
    public void testThatCartBeCleared() throws CartException {
        String cartId = "62dcf10090786e5bdafd7e89";
        Response response = cartService.clearCart(cartId);
        assertEquals("", response.toString());
    }
}