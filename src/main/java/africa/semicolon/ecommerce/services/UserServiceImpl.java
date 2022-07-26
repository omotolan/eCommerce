package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.UserRepository;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User findUserById(Long id) throws UserDoesNotExistException {
        return findUserByIdInternal(id);
    }

    private User findUserByIdInternal(Long id) throws UserDoesNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserDoesNotExistException("User does not exist");
        }
        return user.get();
    }
    @SneakyThrows
    private void userExist(String emailAddress) {
        User user = userRepository.findByEmail(emailAddress);
        if (user != null) {
            throw new UserAlreadyExistException("Email already exist");
        }
    }
}
