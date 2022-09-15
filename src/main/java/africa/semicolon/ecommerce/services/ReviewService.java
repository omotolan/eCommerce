package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Set;

public interface ReviewService {

    Response deleteReview(Long id);
    String addReview(Long productId, String review) throws ProductNotFoundException;
    Set<Review> getAllReviews(Long productId) throws ProductNotFoundException;

//    Response deleteAllProductReview(Long id);
}
