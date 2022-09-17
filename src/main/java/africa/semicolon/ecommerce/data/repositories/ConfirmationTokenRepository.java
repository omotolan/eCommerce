package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository  extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByToken(String token);
}
