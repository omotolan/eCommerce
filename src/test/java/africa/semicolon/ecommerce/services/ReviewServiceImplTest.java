package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.dto.responses.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;



    @Test
    public void testToDeleteAProductReview() {
        Long id = 1L;
        Response response = reviewService.deleteReview(id);

        assertEquals("Review deleted", response.toString());
    }

//    @Test
//    public void testToClearAllProductReview() throws ProductException {
//        var product = productService.findProduct(7L);
//        Response response = reviewService.deleteAllProductReview(product.getId());
//        assertEquals("Review list deleted", response.toString());
//
//    }


}