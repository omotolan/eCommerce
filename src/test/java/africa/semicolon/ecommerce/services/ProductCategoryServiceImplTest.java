package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.ProductCategory;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testThatCategoryCanBeAdded() throws ProductCategoryException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Men's Wear");
        Response response = productCategoryService.addCategory(productCategory.getName());

        assertEquals("PHONE ACCESSORIES added to category", response.toString());
    }
    @Test
    public void testToGetAllCategories(){
        List<ProductCategory> productCategories = productCategoryService.getAllCategories();
    }

}