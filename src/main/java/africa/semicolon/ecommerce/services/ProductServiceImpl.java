package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.data.repositories.ProductRepository;
import africa.semicolon.ecommerce.dto.*;
import africa.semicolon.ecommerce.dto.requests.AddProductRequest;
import africa.semicolon.ecommerce.dto.requests.UpdateProductRequest;
import africa.semicolon.ecommerce.dto.responses.AddProductResponse;
import africa.semicolon.ecommerce.dto.responses.ProductResponse;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductCategoryService productCategoryService;


    private List<ProductCategory> getCategories(List<String> categoryNames) {
        List<ProductCategory> productCategories = new ArrayList<>();
        for (String name : categoryNames) {
            productCategories.add(productCategoryService.findCategoryByName(name));
        }
        return productCategories;
    }

    @Override
    @Transactional
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {

        Product product = modelMapper.map(addProductRequest, Product.class);
        product.setProductCategory(getCategories(addProductRequest.getCategoryNames()));
        product.setDateAdded(LocalDate.now());

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
    public Product getProduct(Long id) throws ProductNotFoundException {
        return findByIdInternal(id);
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
        long total = (long) productDtoList.size();
        Page<ProductDto> page = new PageImpl<>(productDtoList, pageable, total);
        log.info("this is the number: {} ", page.getTotalElements());
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

    public Map<String, Object> findProductsInACategory(Long productCategoryId, Pageable pageable) throws ProductNotFoundException {
        List<Product> products = productRepository.findByProductCategoryId(productCategoryId);
        return returnProductInPages(products, pageable);
    }




}
