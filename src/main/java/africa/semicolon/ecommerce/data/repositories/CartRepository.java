package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
