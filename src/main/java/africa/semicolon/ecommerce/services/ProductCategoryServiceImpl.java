package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.data.repositories.ProductCategoryRepository;
import africa.semicolon.ecommerce.dto.AddCategoryRequest;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public Response addCategory(AddCategoryRequest addCategoryRequest) throws ProductCategoryException {
        findCategory(addCategoryRequest.getName());
        ProductCategory productCategory = modelMapper.map(addCategoryRequest, ProductCategory.class);
        productCategory.setDateCreated(LocalDate.now());
        ProductCategory savedCategory = productCategoryRepository.save(productCategory);

        log.info("new category added {}", savedCategory.getName());
        return new Response(savedCategory.getName() + " added");
    }


    private void findCategory(String name) throws ProductCategoryException {
        ProductCategory productCategory = productCategoryRepository.findByName(name);
        if (productCategory != null) {
            throw new ProductCategoryException("Category already exists");
        }
    }

    @Override
    public List<ProductCategory> getAllCategories() {

        return productCategoryRepository.findAll();
    }

//    @Override
//    public Map<String, Object> getAllProductByCategory(Long id, Pageable pageable) throws ProductNotFoundException {
//        ProductCategory productCategory = productCategoryRepository.findProductCategoriesById(id);
//        List<Product> products = productCategory.getProducts();
//        return productService.returnProductInPages(products, pageable);
//    }
    @Override
    public ProductCategory findCategoryByName(String name){
        return productCategoryRepository.findByName(name);
    }
}
