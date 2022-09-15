package africa.semicolon.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class User {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    @OneToOne
    private Cart cart;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateJoined = LocalDate.now();
    @OneToMany
    private List<Address> addresses = new ArrayList<>();
    private Boolean isVerified = Boolean.FALSE;

    @OneToMany
    private List<PhoneNumber> phoneNumber = new ArrayList<>();
    @OneToMany
    private Set<Role> roles = new HashSet<>();
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<OrderEntity> orderEntities = new ArrayList<>();

}
