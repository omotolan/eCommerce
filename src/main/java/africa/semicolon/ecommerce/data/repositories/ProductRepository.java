package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryName(String categoryName);
}
