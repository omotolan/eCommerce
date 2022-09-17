package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.dto.OrderDTO;
import africa.semicolon.ecommerce.dto.requests.PlaceOrderRequest;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.OrderDoesNotExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(PlaceOrderRequest placeOrderRequest) throws CartException, UserDoesNotExistException;
    OrderDTO getOrder(Long orderId) throws OrderDoesNotExistException;
   List<OrderDTO> findOrderByUserId(Long userId) throws OrderDoesNotExistException;


}
