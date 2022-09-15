package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findByProductCategoryId(Long productCategoryId);

//    Page<Product> findAllByProductCategoryId(Pageable pageable);

//    void findAllByProductCategory(Pageable pageable);
}
