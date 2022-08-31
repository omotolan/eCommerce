package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.data.repositories.ReviewRepository;
import africa.semicolon.ecommerce.dto.Response;
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
    public Response deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        review.ifPresent(reviewRepository::delete);

        log.info("a review was deleted");
        return new Response("Review deleted");
    }

//    @Override
//    public Response deleteAllProductReview(Long productId) {
//        List<Review> reviews = reviewRepository.findByProductId(productId);
//        reviewRepository.deleteAll(reviews);
//
//        log.info("all reviews were deleted");
//        return new Response("Review list deleted");
//    }
}
