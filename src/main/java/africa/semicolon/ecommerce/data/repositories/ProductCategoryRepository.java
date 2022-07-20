package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
}
