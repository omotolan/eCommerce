package africa.semicolon.ecommerce.data.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Validated
public class Address {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    @NotNull
    private String houseNumber;
    @NotBlank
    @NotNull
    private String street;
    @NotBlank
    @NotNull
    private String city;
    @NotBlank
    @NotNull
    private String state;
    private String country;
    private String zipCode;
}
