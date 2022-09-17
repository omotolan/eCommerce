package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.dto.*;
import africa.semicolon.ecommerce.dto.requests.AddProductRequest;
import africa.semicolon.ecommerce.dto.requests.UpdateProductRequest;
import africa.semicolon.ecommerce.dto.responses.AddProductResponse;
import africa.semicolon.ecommerce.dto.responses.ProductResponse;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private  ProductService productService;


    @Test
    public void testThatProductCanBeAdded() throws ProductNotFoundException {
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("phones");
//        categoryNames.add("gadgets");
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setName("samsung");
        addProductRequest.setDescription("7th gen dell laptop");
        addProductRequest.setImageUrl("image.com");
        addProductRequest.setQuantity(5);
        addProductRequest.setCategoryNames(categoryNames);
        addProductRequest.setPrice(new BigDecimal(3000));
        AddProductResponse addProductResponse = productService.addProduct(addProductRequest);

        assertEquals("Dell Latitude successfully added", addProductResponse.getMessage());
    }

    @Test
    public void testToRemoveProductById() throws ProductNotFoundException {
        Long id = 1L;
        Response response = productService.deleteProductById(id);
        assertEquals("Dell Latitude successfully deleted", response.toString());
    }

    @Test
    public void testToFindProductById() throws ProductNotFoundException {
        var id = 1L;

        ProductDto productDto = productService.getProductById(id);

        assertEquals("", productDto.getName());

    }


    @Test
    public void testThatProductCanBeUpdated() throws ProductNotFoundException {
        var id = 4L;

        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                .name("Samsung s20")
                .quantity(2)
                .description("brand new phone")
                .price(new BigDecimal(500000))
                .imageUrl("samsungS20.com")
                .build();
        ProductResponse response = productService.updateProduct(id, updateProductRequest);
        assertEquals("Product successfully updated", response.toString());
    }

    @Test
    public void testToFindProductByName() throws ProductNotFoundException {
        Pageable pageable = PageRequest.of(1, 5);
        Map<String, Object> pro = productService.findProductByName("iphone 12", pageable);


//        assertEquals("", pro.get("numberOfElementInPage"));
        assertEquals("1", pro.get("totalNumberOfElementsInDataBase"));
    }

}