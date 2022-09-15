package africa.semicolon.ecommerce.controller;

import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import africa.semicolon.ecommerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ReviewController {

    private final ReviewService reviewService;



    @PostMapping("addReview/{id}")
    public ResponseEntity<?> addReview(@PathVariable String  id, @RequestBody String review) throws ProductNotFoundException {
        var response =  reviewService.addReview(Long.parseLong(id), review);

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("allReviews/{id}")
    public ResponseEntity<?> getAllReview(@PathVariable String id) throws ProductNotFoundException {
        var response = reviewService.getAllReviews(Long.parseLong(id));
        System.out.println("this is the size: " + response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
