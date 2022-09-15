package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Item;
import africa.semicolon.ecommerce.dto.AddItemRequest;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.UpdateItemInCartRequest;
import africa.semicolon.ecommerce.exceptions.CartException;

import java.math.BigDecimal;

public interface CartService {
    void createCart();
    String addToCart(Long cartId,AddItemRequest addItemRequest) throws CartException;


//    Response addItemToCart(Long cartId, Long productId, int quantity) throws ProductException, CartException;
//
    BigDecimal getCartTotal(Long cartId) throws CartException;
//
    Response removeItemFromCart(Long cartId, Long productId) throws  CartException;
//
    Response reduceItemQuantityInCart(Long cartId, UpdateItemInCartRequest updateItemInCartRequest) throws CartException;
//
    Response clearCart(Long cartId) throws CartException;
}
