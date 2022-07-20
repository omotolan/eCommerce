package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}
