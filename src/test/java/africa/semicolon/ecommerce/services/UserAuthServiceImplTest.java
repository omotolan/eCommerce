package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Address;
import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.dto.requests.SignUpRequest;
import africa.semicolon.ecommerce.dto.responses.SignUpResponse;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAuthServiceImplTest {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private RoleService roleService;

    @Test
    public void testThatUserCanSignUp() throws UserAlreadyExistException, UnirestException {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(4L).get());
        Address address = Address.builder()
                .city("yaba")
                .country("nigeria")
                .houseNumber("23")
                .zipCode("121343")
                .state("Lagos")
                .street("emily akinola")
                .build();
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("akinsolatolani@yahoooo.com")
                .firstName("tolani")
                .lastName("akinsola")
                .phoneNumber("08101791765")
                .password("123456")
                .address(address)
                .dateOfBirth("2022-09-14")
                .roles(roles)
                .build();
       SignUpResponse signUpResponse = userAuthService.signUp(signUpRequest);
       assertEquals("tolani", signUpResponse.getUserDto().getFirstName());
    }

    @Test
    void confirmToken() {
    }
}