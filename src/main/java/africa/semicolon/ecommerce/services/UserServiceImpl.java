package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.UserRepository;
import africa.semicolon.ecommerce.dto.requests.CreateUserRequest;
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
      User user =  userRepository.findByEmail(emailAddress);
      if (user != null){
          throw new UserAlreadyExistException("Email already exist");
      }
    }
}
