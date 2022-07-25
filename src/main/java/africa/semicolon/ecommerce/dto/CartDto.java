package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Item item;
    private int quantity;
}
