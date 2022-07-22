package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.dto.ProductDto;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.UpdateProductDto;
import africa.semicolon.ecommerce.exceptions.ProductException;

import java.util.List;

public interface ProductService {
    Response addProduct(ProductDto productDto);

    Response deleteProductById(String id) throws ProductException;

    ProductDto getProductById(String id) throws ProductException;

    List<ProductDto> getAllProduct();

    Response updateProduct(String id, UpdateProductDto updateProductDto);

    Product findProduct(String id) throws ProductException;

    List<ProductDto> getAllProductsInACategory(String categoryName) throws ProductException;
}
