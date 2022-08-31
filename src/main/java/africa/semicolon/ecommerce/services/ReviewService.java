package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Review;
import africa.semicolon.ecommerce.dto.Response;

import java.util.List;

public interface ReviewService {

    Response deleteReview(Long id);

//    Response deleteAllProductReview(Long id);
}
