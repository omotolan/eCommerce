package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import africa.semicolon.ecommerce.data.repositories.ConfirmationTokenRepository;
import africa.semicolon.ecommerce.exceptions.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken getToken(String token) throws TokenException {
        return findToken(token);
    }

    @Override
    public ConfirmationToken findById(Long id) throws TokenException {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findById(id);
        if (confirmationToken.isEmpty()) {
            throw new TokenException("token not found");
        }
        return confirmationToken.get();
    }

    private ConfirmationToken findToken(String token) throws TokenException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if (confirmationToken == null) {
            throw new TokenException("token not found");
        }
        return confirmationToken;

    }

    @Override
    public void deleteUsedToken() {

        List<ConfirmationToken> confirmationTokenList = confirmationTokenRepository.findAll().stream()
                .filter(eachFoundToken ->
                        eachFoundToken.getUser().getIsVerified()).toList();
        confirmationTokenRepository.deleteAll(confirmationTokenList);

    }
}
