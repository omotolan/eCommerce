package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;

import java.util.List;

public interface ProductCategoryService {
    Response addCategory(String name) throws ProductCategoryException;

    List<ProductCategory> getAllCategories();
}
