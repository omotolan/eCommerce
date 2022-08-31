package africa.semicolon.ecommerce.controller;

import africa.semicolon.ecommerce.dto.AddCategoryRequest;
import africa.semicolon.ecommerce.exceptions.ProductCategoryException;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import africa.semicolon.ecommerce.services.ProductCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("api/v1/productCategories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;


    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) throws ProductCategoryException {
        try {
            var serviceResponse = productCategoryService.addCategory(addCategoryRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        } catch (ProductCategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProducts/{id}/{pageNo}/{noOfItems}")
    public ResponseEntity<?> getAllProducts(@PathVariable String id
            , @PathVariable(value = "pageNo", required = false) @DefaultValue({"0"}) String pageNo,
                                            @PathVariable(value = "noOfItems", required = false) @DefaultValue({"10"}) @Max(10) String numberOfItems) throws ProductNotFoundException {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(numberOfItems));
        var serviceResponse = productCategoryService.getAllProductByCategory(Long.parseLong(id), pageable);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);


    }
}
