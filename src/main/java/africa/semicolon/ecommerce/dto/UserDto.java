package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private Address addresses;
    private String phoneNumber;
}
