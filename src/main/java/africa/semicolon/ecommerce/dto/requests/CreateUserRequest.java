package africa.semicolon.ecommerce.dto.requests;

import africa.semicolon.ecommerce.data.model.Address;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Setter
@Getter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address addresses;
    private String phoneNumber;
}
