package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.dto.requests.AddCategoryRequest;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;

import java.util.List;

public interface ProductCategoryService {
    Response addCategory(AddCategoryRequest addCategoryRequest) throws ProductCategoryException;

    List<ProductCategory> getAllCategories();
//    Map<String, Object> getAllProductByCategory(Long id, Pageable pageable) throws ProductNotFoundException;
    ProductCategory findCategoryByName(String name);
}
