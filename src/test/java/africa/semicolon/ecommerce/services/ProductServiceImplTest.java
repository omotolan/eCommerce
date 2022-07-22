package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.dto.ProductDto;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.UpdateProductDto;
import africa.semicolon.ecommerce.exceptions.ProductException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testThatProductCanBeAdded() {
        ProductDto productDto = ProductDto.builder()
                .name("Iphone 13")
                .categoryName("Phone")
                .description("london used iphone 13")
                .price(new BigDecimal("300000"))
                .image("myimage.com")
                .quantity(4)
                .build();
        Response response = productService.addProduct(productDto);
        assertEquals("Iphone 13 successfully added to Phone Category", response.toString());
    }

    @Test
    public void testToRemoveProductById() throws ProductException {
        Product product = new Product();
        product.setId("62d9e1a24f4a91591b157a51");
        Response response = productService.deleteProductById(product.getId());
        assertEquals("Iphone 13 successfully deleted", response.toString());
    }

    @Test
    public void testToFindProductById() throws ProductException {
        Product product = new Product();
        product.setId("62d9e3caa2755e3e4c948864");
        ProductDto productDto = productService.getProductById(product.getId());

        assertEquals("", productDto.toString());

    }

    @Test
    public void testToGetAllProduct() {
        List<ProductDto> productDtos = productService.getAllProduct();
        assertEquals(3, productDtos.size());
        assertEquals("", productDtos.toString());
    }

    @Test
    public void testThatProductCanBeUpdated() {
        Product product = new Product();
        product.setId("62d9e3caa2755e3e4c948864");
        UpdateProductDto updateProductDto = UpdateProductDto.builder()
                .name("Samsung s20")
                .quantity(7)
                .description("brand new phone")
                .price(new BigDecimal(500000))
                .image("samsungS20.com")
                .build();
        Response response = productService.updateProduct(product.getId(), updateProductDto);
        assertEquals("Product successfully updated", response.toString());
    }

    @Test
    public void testThatProductCanBeSearched() throws ProductException {
        Product product = new Product();
        product.setId("62d9e3caa2755e3e4c948864");
        Product product1 = productService.findProduct(product.getId());
        assertEquals("", product1.toString());
    }

    @Test
    public void testToGetAllProductsInACategory() throws ProductException {
        Product product = new Product();
        product.setCategoryName("Phone");
        List<ProductDto> productDto = productService.getAllProductsInACategory(product.getCategoryName());
        assertEquals(3, productDto.size());
    }

}