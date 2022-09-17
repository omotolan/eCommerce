package africa.semicolon.ecommerce.controller;

import africa.semicolon.ecommerce.dto.requests.AddCategoryRequest;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import africa.semicolon.ecommerce.services.ProductCategoryServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productCategories")
public class ProductCategoryController {

    private final ProductCategoryServiceImpl productCategoryService;


    @PostMapping("/addCategory")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        try {
            var serviceResponse = productCategoryService.addCategory(addCategoryRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        } catch (ProductCategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
