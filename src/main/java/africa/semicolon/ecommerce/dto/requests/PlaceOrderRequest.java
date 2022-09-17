package africa.semicolon.ecommerce.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PlaceOrderRequest {
    @NotBlank
    @NotNull
    private Long cartId;
    @NotBlank
    @NotNull
    private Long userId;
    private Boolean isPayOnDelivery;
    private String paymentLink;
}
