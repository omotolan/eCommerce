package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.data.repositories.ProductRepository;
import africa.semicolon.ecommerce.dto.*;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {

        Product product = modelMapper.map(addProductRequest, Product.class);

        Product savedProduct = productRepository.save(product);
        log.info("product added {}", savedProduct.getName());
        ProductDto productDto = modelMapper.map(savedProduct, ProductDto.class);

        return new AddProductResponse(productDto, savedProduct.getName() + " successfully added");
    }

    @Override
    public Response deleteProductById(Long id) throws ProductNotFoundException {
        Product product = findByIdInternal(id);

        productRepository.delete(product);

        log.info("product with id: " + id + " deleted");
        return new Response(product.getName() + " successfully deleted");
    }

    private Product findByIdInternal(Long id) throws ProductNotFoundException {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty()) {
            throw new ProductNotFoundException("Product does not exist");
        }
        return foundProduct.get();
    }

    @Override
    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        return ProductDto.packDto(findByIdInternal(id));
    }


    @Override
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) throws ProductNotFoundException {
        Product foundProduct = findByIdInternal(id);

        if (updateProductRequest != null) {
            foundProduct.setName(updateProductRequest.getName());
            foundProduct.setImageUrl(updateProductRequest.getImageUrl());
            foundProduct.setDescription(updateProductRequest.getDescription());
            foundProduct.setProductCategory(updateProductRequest.getProductCategory());
            foundProduct.setQuantity(updateProductRequest.getQuantity());
            foundProduct.setPrice(updateProductRequest.getPrice());
        }

        Product updatedProduct = productRepository.save(foundProduct);

        log.info("product with id: " + id + " was updated on: " + LocalDateTime.now());
        return new ProductResponse("Product successfully updated", updatedProduct);
    }

    @Override
    public Map<String, Object> findProductByName(String name, Pageable pageable) throws ProductNotFoundException {
        List<Product> products = productRepository.findByName(name);

        return returnProductInPages(products, pageable);

    }

    @Override
    public Map<String, Object> returnProductInPages(List<Product> products, Pageable pageable) throws ProductNotFoundException {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(ProductDto.packDto(product));
        }
        System.out.println("this is the size: " + productDtoList.size());
        Page<ProductDto> page = new PageImpl<>(productDtoList, pageable, products.size());
        log.info("this is the number: {} ", page.getTotalElements());
        if (page.getTotalElements() == 0) {
            throw new ProductNotFoundException("No product");
        }
        Map<String, Object> pageResult = new HashMap<>();
        pageResult.put("totalNumberOfPages", page.getTotalPages());
        pageResult.put("totalNumberOfElementsInDataBase", page.getTotalElements());
        if (page.hasNext()) {
            pageResult.put("nextPage", page.nextPageable());
        }
        if (page.hasPrevious()) {
            pageResult.put("previousPage", page.previousPageable());
        }

        pageResult.put("product", page.getContent());
        pageResult.put("numberOfElementInPage", page.getNumberOfElements());
        log.info("number of elements {}", page.getNumberOfElements());
        pageResult.put("pageNumber", page.getNumber());
        pageResult.put("size", page.getSize());
        return pageResult;
    }

    @Override
    public String addReview(Long id, String review) throws ProductNotFoundException {
        Product product = findByIdInternal(id);
        Review addReview = new Review(review);
        product.getReviews().add(addReview);
        productRepository.save(product);

        return "review added";

    }

    @Override
    public Set<Review> getAllReviews(Long id) throws ProductNotFoundException {
        Product product = findByIdInternal(id);
        return product.getReviews();

    }


}
