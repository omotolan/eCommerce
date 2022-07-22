package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.ReviewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testThatReviewCanBeAdded() {
        Product product = new Product();
        product.setId("123");

        ReviewDto reviewDto = ReviewDto.builder()
                .productId(product.getId())
                .review("the product is bad")
                .build();
        Response response = reviewService.addReview(reviewDto);
        assertEquals("Review added", response.toString());
    }

    @Test
    public void testToGetAllReview() {
        Product product = new Product();
        product.setId("123");
        List<Review> reviews = reviewService.getProductReview(product.getId());
        assertEquals(1, reviews.size());
    }

    @Test
    public void testToDeleteAProductReview() {
        Review review = new Review();
        review.setId("62d9d6ecb3083b2b670c9a5a");
        Response response = reviewService.deleteReview(review.getId());

        assertEquals("Review deleted", response.toString());
    }

    @Test
    public void testToClearAllProductReview() {
        Product product = new Product();
        product.setId("123");
        Response response = reviewService.deleteAllProductReview(product.getId());
        assertEquals("Review list deleted", response.toString());

    }


}