package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
