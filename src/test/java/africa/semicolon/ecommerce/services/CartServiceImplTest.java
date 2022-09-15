package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.repositories.CartRepository;
import africa.semicolon.ecommerce.dto.AddItemRequest;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;



    @BeforeEach
    void setUp() {
//        cartRepository.deleteAll();
//        productRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
//        cartRepository.deleteAll();
//        productRepository.deleteAll();
    }

    @Test
    public void testThatCartCanBeCreated() {
        cartService.createCart();
        assertEquals(1, cartRepository.count());
    }


    @Test
    public void testThatItemCanBeAddedToCart() throws CartException, ProductNotFoundException {

      Product product =  productService.getProduct(1L);
        AddItemRequest addItemRequest = new AddItemRequest();
        addItemRequest.setProduct(product);
        addItemRequest.setQuantity(2);
        var cardId = 1L;

       var response = cartService.addToCart(cardId, addItemRequest);

        assertEquals("Samsung s20 added to cart", response);

    }

    @Test
    public void testToGetCartTotal() throws CartException {
        Long cartId = 1L;
        BigDecimal total = cartService.getCartTotal(cartId);
        assertEquals(new BigDecimal("12000.00"), total);
    }

    @Test
    public void testThatItemCanBeRemovedFromCart() throws CartException {
//        Item item = new Item();
//        item.setQuantity(3);
//        item.setProduct(addItemRequest.getProduct());
//        Response responses = cartService.removeItemFromCart(addItemRequest.getCartId(), item);
//        assertEquals("Samsung s20 removed from cart", responses.toString());
    }
//
    @Test
    public void testToReduceQuantityOfAnItem() throws CartException {
//        Item item = new Item();
//        item.setQuantity(3);
//        item.setProduct(addItemRequest.getProduct());
//
//
//        Response response1 = cartService.reduceItemQuantityInCart(addItemRequest.getCartId(), item);

    }

    @Test
    public void testThatCartBeCleared() throws CartException {
        Long cartId = 1L;
        Response response = cartService.clearCart(cartId);
        assertEquals("cart cleared", response.toString());
    }
}