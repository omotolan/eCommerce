package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
