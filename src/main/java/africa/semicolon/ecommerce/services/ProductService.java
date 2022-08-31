package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.dto.*;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {

    Response deleteProductById(Long id) throws ProductNotFoundException;

    ProductDto getProductById(Long id) throws ProductNotFoundException;

    ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) throws ProductNotFoundException;

    Map<String, Object> findProductByName(String name, Pageable pageable) throws ProductNotFoundException;

    AddProductResponse addProduct(AddProductRequest addProductRequest);
    String addReview(Long id, String review) throws ProductNotFoundException;
    Set<Review> getAllReviews(Long id) throws ProductNotFoundException;
    Map<String, Object> returnProductInPages(List<Product> products, Pageable pageable) throws ProductNotFoundException;

}
