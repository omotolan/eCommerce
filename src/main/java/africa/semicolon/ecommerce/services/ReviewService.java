package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    Response addReview(ReviewDto reviewDto);

    List<Review> getProductReview(String id);

    Response deleteReview(String id);

    Response deleteAllProductReview(String id);
}
