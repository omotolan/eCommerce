package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.OrderEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderDTO {
    private String deliveryAddress;
    private BigDecimal orderTotal;

    private LocalDate dateCreated;

//    public static UserOrder UnpackDto(OrderDTO orderDTO){
//        return UserOrder.builder()
//                .orderTotal(orderDTO.getOrderTotal())
//                .deliveryAddress(orderDTO.getDeliveryAddress())
//                .dateCreated(orderDTO.getDateCreated())
//                .build();
//
//    }
    public static OrderDTO packDto(OrderEntity order){
        return OrderDTO.builder()
                .orderTotal(order.getOrderTotal())
                .dateCreated(order.getDateCreated())
                .orderTotal(order.getOrderTotal())
                .build();

    }
}
