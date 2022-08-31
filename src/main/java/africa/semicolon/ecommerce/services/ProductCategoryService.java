package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.dto.AddCategoryRequest;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {
    Response addCategory(AddCategoryRequest addCategoryRequest) throws ProductCategoryException;

    List<ProductCategory> getAllCategories();
    Map<String, Object> getAllProductByCategory(Long id, Pageable pageable) throws ProductNotFoundException;
}
