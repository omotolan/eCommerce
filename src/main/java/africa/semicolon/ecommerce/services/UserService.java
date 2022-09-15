package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.dto.CreateUserRequest;
import africa.semicolon.ecommerce.dto.UserDto;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;

public interface UserService {
    UserDto createUser(CreateUserRequest createUserRequest) throws UserAlreadyExistException;
    User findUserById(Long id) throws UserDoesNotExistException;
}
