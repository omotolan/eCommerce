package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
