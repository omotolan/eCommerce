package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import africa.semicolon.ecommerce.exceptions.TokenException;

public interface TokenService {
    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getToken(String token) throws TokenException;

    ConfirmationToken findById(Long id) throws TokenException;

    void deleteUsedToken();
}
