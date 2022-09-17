package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.dto.OrderDTO;
import africa.semicolon.ecommerce.dto.requests.PlaceOrderRequest;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.OrderDoesNotExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;


    @Test
    public void testThatOrderCanBePlaced() throws CartException, UserDoesNotExistException {
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
        placeOrderRequest.setCartId(1L);
        placeOrderRequest.setUserId(2L);
        placeOrderRequest.setIsPayOnDelivery(Boolean.TRUE);
        OrderDTO orderDTO = orderService.placeOrder(placeOrderRequest);
        assertEquals(new BigDecimal("6000.00"), orderDTO.getOrderTotal());


    }

    @Test
    public void testToFindOrderById() throws OrderDoesNotExistException {
        Long orderId = 1L;
        OrderDTO orderDTO = orderService.getOrder(orderId);
        assertEquals(new BigDecimal("6000.00"), orderDTO.getOrderTotal());
    }

    @Test
    public void testToFindOrderByUserId() throws OrderDoesNotExistException {
        Long userId = 2L;
        List<OrderDTO> orderDTO = orderService.findOrderByUserId(userId);
        assertEquals(2, orderDTO.size());
    }


}