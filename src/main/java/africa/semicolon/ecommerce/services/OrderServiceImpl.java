package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.OrderEntity;
import africa.semicolon.ecommerce.data.model.Transaction;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.OrderRepository;
import africa.semicolon.ecommerce.dto.OrderDTO;
import africa.semicolon.ecommerce.dto.requests.PlaceOrderRequest;
import africa.semicolon.ecommerce.exceptions.CartException;
import africa.semicolon.ecommerce.exceptions.OrderDoesNotExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    private final CartService cartService;

    @Transactional
    @Override
    public OrderDTO placeOrder(PlaceOrderRequest placeOrderRequest) throws CartException, UserDoesNotExistException {
        Cart cart = cartService.getCartById(placeOrderRequest.getCartId());
        User user = userService.findUserById(placeOrderRequest.getUserId());
        OrderEntity orderEntity = OrderEntity.builder()
                .orderTotal(cart.getTotal())
                .user(user)
                .dateCreated(LocalDate.now())
                .deliveryAddress(user.getAddress())
                .build();

        if (placeOrderRequest.getIsPayOnDelivery()) {
            orderEntity.setIsPayOnDelivery(Boolean.TRUE);
            OrderEntity savedOrder = orderRepository.save(orderEntity);
            return OrderDTO.packDto(savedOrder);
        } else {
            /*
             * call an external api to make payment and confirm if payment was successful
             * */
            Transaction transaction = new Transaction();
            if (transactionService.verifyTransaction(placeOrderRequest.getPaymentLink())) {

                OrderEntity savedOrder = orderRepository.save(orderEntity);
                transaction.setLocalDateTime(LocalDateTime.now());
                transaction.setPaymentLink(placeOrderRequest.getPaymentLink());
                transaction.setOrderEntity(savedOrder);
                transactionService.saveTransaction(transaction);
                return OrderDTO.packDto(savedOrder);
            }
        }

        return null;
    }


    @Override
    public OrderDTO getOrder(Long orderId) throws OrderDoesNotExistException {
        return OrderDTO.packDto(findOrderById(orderId));
    }

    private OrderEntity findOrderById(Long orderId) throws OrderDoesNotExistException {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderDoesNotExistException("Order does not exist");
        }
        return order.get();
    }

    @Override
    public List<OrderDTO> findOrderByUserId(Long userId) throws OrderDoesNotExistException {
        List<OrderEntity> order = orderRepository.findByUserId(userId);
        if (order == null) {
            throw new OrderDoesNotExistException("No Order");
        }

        return order.stream()
                .map(OrderDTO::packDto)
                .collect(Collectors.toList());
    }


}
