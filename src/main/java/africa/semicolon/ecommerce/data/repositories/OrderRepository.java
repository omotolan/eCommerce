package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
