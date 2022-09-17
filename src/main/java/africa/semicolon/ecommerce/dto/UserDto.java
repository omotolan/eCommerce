package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Address;
import africa.semicolon.ecommerce.data.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateJoined;

    public  static UserDto pack(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .dateJoined(user.getDateJoined())
                .build();
    }
    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
