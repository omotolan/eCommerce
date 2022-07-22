package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.data.repositories.ReviewRepository;
import africa.semicolon.ecommerce.dto.Response;
import africa.semicolon.ecommerce.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;

    @Override
    public Response addReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setProductId(reviewDto.getProductId());
        review.setReview(reviewDto.getReview());

        reviewRepository.save(review);
        log.info("a new review was added");

        return new Response("Review added");
    }

    @Override
    public List<Review> getProductReview(String productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Response deleteReview(String reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        review.ifPresent(reviewRepository::delete);

        log.info("a review was deleted");
        return new Response("Review deleted");
    }

    @Override
    public Response deleteAllProductReview(String productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        reviewRepository.deleteAll(reviews);

        log.info("all reviews were deleted");
        return new Response("Review list deleted");
    }
}
