package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.User;

import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;

public interface UserService {
    User findUserById(Long id) throws UserDoesNotExistException;
}
