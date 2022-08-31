package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByName(String name);
    ProductCategory findProductCategoriesById(Long id);

}
