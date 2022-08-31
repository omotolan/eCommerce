package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
