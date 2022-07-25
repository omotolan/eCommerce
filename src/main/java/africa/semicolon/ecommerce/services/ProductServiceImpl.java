package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.repositories.ProductRepository;
import africa.semicolon.ecommerce.dto.ProductDto;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.UpdateProductDto;
import africa.semicolon.ecommerce.exceptions.ProductException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Response addProduct(ProductDto productDto) throws ProductException {
        Product product = productRepository.findByName(productDto.getName());

        if (product != null) {
            throw new ProductException("Product already exists");
        }

        product = ProductDto.unPackDto(productDto);
        productRepository.save(product);
        log.info("new product added");
        return new Response(product.getName() + " successfully added to " + product.getCategoryName()
                + " Category");
    }

    @Override
    public Response deleteProductById(String id) throws ProductException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product does not exist");
        }
        String productName = product.get().getName();
        productRepository.delete(product.get());

        log.info("product with id: " + id + " deleted");
        return new Response(productName + " successfully deleted");
    }

    @Override
    public ProductDto getProductById(String id) throws ProductException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product does not exist");
        }
        return ProductDto.packDto(product.get());
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productDto.add(ProductDto.packDto(product));
        }

        return productDto;
    }

    @Override
    public Response updateProduct(String id, UpdateProductDto updateProductDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent() && updateProductDto != null) {
            if (updateProductDto.getName().length() != 0) {
                product.get().setName(updateProductDto.getName());
            } else if (updateProductDto.getImage().length() != 0) {
                product.get().setImage(updateProductDto.getImage());
            } else if (updateProductDto.getDescription().length() != 0) {
                product.get().setDescription(updateProductDto.getDescription());
            } else if (updateProductDto.getCategoryName().length() != 0) {
                product.get().setCategoryName(updateProductDto.getCategoryName());
            } else if (updateProductDto.getQuantity() > 0) {
                product.get().setQuantity(updateProductDto.getQuantity());
            } else if (updateProductDto.getPrice().compareTo(new BigDecimal(0)) > 0) {
                product.get().setPrice(updateProductDto.getPrice());
            }
        }
        product.ifPresent(productRepository::save);
        log.info("product with id: " + id + " was updated on: " + LocalDateTime.now());
        return new Response("Product successfully updated");
    }

    @Override
    public Product findProduct(String id) throws ProductException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product does not exist");
        }
        return product.get();
    }
    @Override
    public Product findProductByName(String productName) throws ProductException {
        Product product = productRepository.findByName(productName);
        if (product == null) {
            throw new ProductException("Product does not exist");
        }
        return product;

    }

    @Override
    public List<ProductDto> getAllProductsInACategory(String categoryName) throws ProductException {
        List<Product> products = productRepository.findByCategoryName(categoryName.toUpperCase());
        if (products.isEmpty()) {
            throw new ProductException("No product in this category");
        }
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(ProductDto.packDto(product));
        }
        return productDtos;
    }
}
