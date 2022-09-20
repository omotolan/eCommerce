package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.dto.*;
import africa.semicolon.ecommerce.dto.requests.AddProductRequest;
import africa.semicolon.ecommerce.dto.requests.UpdateProductRequest;
import africa.semicolon.ecommerce.dto.responses.AddProductResponse;
import africa.semicolon.ecommerce.dto.responses.ProductResponse;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Response deleteProductById(Long productId) throws ProductNotFoundException;

    ProductDto getProductById(Long productId) throws ProductNotFoundException;

    ProductResponse updateProduct(Long productId, UpdateProductRequest updateProductRequest) throws ProductNotFoundException;

    Map<String, Object> findProductByName(String name, Pageable pageable) throws ProductNotFoundException;

    AddProductResponse addProduct(AddProductRequest addProductRequest);

    Map<String, Object> returnProductInPages(List<Product> products, Pageable pageable);

    Product getProduct(Long productId) throws ProductNotFoundException;

    Map<String, Object> findProductsInACategory(Long productCategoryId, Pageable pageable);
}
