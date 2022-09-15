package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Set<Review> findByProductId(Long productId);
//    List<Review> findByProductId(Long productId);
}
