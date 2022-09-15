package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.UserRepository;
import africa.semicolon.ecommerce.dto.CreateUserRequest;
import africa.semicolon.ecommerce.dto.ProductDto;
import africa.semicolon.ecommerce.dto.UserDto;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) throws UserAlreadyExistException {
        userExist(createUserRequest.getEmailAddress());
        User user = User.builder()
                .addresses(createUserRequest.getAddresses())
                .emailAddress(createUserRequest.getEmailAddress())
                .firstName(createUserRequest.getFirstName())
                .dateJoined(LocalDate.now())
                .lastName(createUserRequest.getLastName())
                .password(createUserRequest.getPassword())
                .password(createUserRequest.getPassword())
                .isVerified(Boolean.FALSE)
                .phoneNumber(createUserRequest.getPhoneNumber())
                .cart(new Cart())
                .build();
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);


    }
    @Override
    public User findUserById(Long id) throws UserDoesNotExistException {
        return findUserByIdInternal(id);
    }
    private User findUserByIdInternal(Long id) throws UserDoesNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserDoesNotExistException("User does not exist");
        }
        return user.get();
    }
    private void userExist(String emailAddress) throws UserAlreadyExistException {
      User user =  userRepository.findByEmailAddress(emailAddress);
      if (user != null){
          throw new UserAlreadyExistException("Email already exist");
      }
    }
}
