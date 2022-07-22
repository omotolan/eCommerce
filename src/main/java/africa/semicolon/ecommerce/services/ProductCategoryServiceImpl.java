package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.data.repositories.ProductCategoryRepository;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Response addCategory(String name) throws ProductCategoryException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name.toUpperCase());

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findByName(productCategory.getName());

        if (optionalProductCategory.isPresent()) {
            throw new ProductCategoryException("Category already exists");
        }

        productCategoryRepository.save(productCategory);

        log.info("new category added");
        return new Response(productCategory.getName() + " added to category");
    }

    @Override
    public List<ProductCategory> getAllCategories() {

        return productCategoryRepository.findAll();
    }
}
