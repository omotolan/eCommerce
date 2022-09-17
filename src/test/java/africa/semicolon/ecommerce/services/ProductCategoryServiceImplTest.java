package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.dto.requests.AddCategoryRequest;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testThatCategoryCanBeAdded() throws ProductCategoryException {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest();
        addCategoryRequest.setName("phones");
        Response response = productCategoryService.addCategory(addCategoryRequest);

        assertEquals("PHONE ACCESSORIES added to category", response.toString());
    }

    @Test
    public void testToGetAllCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllCategories();
        assertEquals(2, productCategories.size());
    }

//    @Test
//    public void testToGetAllProductsInACategory() throws ProductNotFoundException {
//        Long id = 1L;
//        Pageable pageable = PageRequest.of(2, 5);
//        var response = productCategoryService.getAllProductByCategory(id, pageable);
//        assertEquals(0, response.size());
//    }

}