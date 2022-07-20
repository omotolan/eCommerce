package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
