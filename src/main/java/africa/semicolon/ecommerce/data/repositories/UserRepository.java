package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findUserByEmailIgnoreCase(String email);
}
