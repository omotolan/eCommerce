package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.data.repositories.ReviewRepository;
import africa.semicolon.ecommerce.dto.responses.Response;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;


    @Override
    public Response deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        review.ifPresent(reviewRepository::delete);

        log.info("a review was deleted");
        return new Response("Review deleted");
    }
    @Override
    @Transactional
    public String addReview(Long productId, String comment) throws ProductNotFoundException {
       Product product = productService.getProduct(productId);
        Review review = new Review(comment);
        review.setProduct(product);
        review.setDateAdded(LocalDate.now());
        reviewRepository.save(review);

        return "review added";

    }

    @Override
    public Set<Review> getAllReviews(Long productId) {
        return reviewRepository.findByProductId(productId);

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
