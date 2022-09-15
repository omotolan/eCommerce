package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Address;
import africa.semicolon.ecommerce.dto.CreateUserRequest;
import africa.semicolon.ecommerce.dto.UserDto;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void createUser() {

        Address address = new Address();
        address.setCity("yaba");
        address.setCountry("Nigeria");
        address.setState("Lagos");
        address.setHouseNumber("23");
        address.setZipCode("24342323");
        address.setStreet("alagomeji");
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .addresses(address)
                .emailAddress("akinsolatolani@yahoo.com")
                .firstName("tolani")
                .lastName("akinsola")
                .password("ddiuaia")
                .phoneNumber("08032980232")
                .build();
        try {


            UserDto userDto = userService.createUser(createUserRequest);
            assertEquals("tolani", userDto.getFirstName());
        } catch (UserAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }
}