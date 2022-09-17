package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.dto.requests.SignUpRequest;
import africa.semicolon.ecommerce.dto.responses.SignUpResponse;
import africa.semicolon.ecommerce.exceptions.TokenException;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface UserAuthService {
    SignUpResponse signUp(SignUpRequest signUpRequest) throws UnirestException, UserAlreadyExistException;
    String confirmToken(String token) throws TokenException;
    User getUserByEmail(String email) throws UserDoesNotExistException;
    ConfirmationToken findById(Long id) throws TokenException;
}
