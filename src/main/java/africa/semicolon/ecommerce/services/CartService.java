package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Item;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.ProductException;

import java.math.BigDecimal;

public interface CartService {
    void createCart();

    Response addItemToCart(String cartId,Item item) throws ProductException, CartException;

    BigDecimal getCartTotal(String cartId) throws CartException;

    Response removeItemFromCart(String cartId, Item item) throws ProductException, CartException;

    Response reduceItemQuantityInCart(String cartId, Item item, int quantity) throws CartException, ProductException;

    Response clearCart(String cartId) throws CartException;
}
