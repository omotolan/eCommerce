package africa.semicolon.ecommerce.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("users")
public class User {
    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String cartId;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private List<Address> addresses = new ArrayList<>();
    private List<String> phoneNumber = new ArrayList<>();
    @DBRef
    private Set<Role> roles = new HashSet<>();
    @DBRef
    private List<Order> orders = new ArrayList<>();

}
