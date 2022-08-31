package africa.semicolon.ecommerce.controller;

import africa.semicolon.ecommerce.dto.AddProductRequest;
import africa.semicolon.ecommerce.dto.UpdateProductRequest;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import africa.semicolon.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest) {
        var response = productService.addProduct(addProductRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) throws ProductNotFoundException {
        var response = productService.getProductById(Long.parseLong(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest updateProductRequest) throws ProductNotFoundException {
        var response = productService.updateProduct(Long.parseLong(id), updateProductRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        var response = productService.deleteProductById(Long.parseLong(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{name}/{pageNo}/{noOfItems}")
    public ResponseEntity<?> findProductByName(@PathVariable String name,
                                               @PathVariable(value = "pageNo", required = false) @DefaultValue({"0"}) String pageNo,
                                               @PathVariable(value = "noOfItems", required = false) @DefaultValue({"10"}) @Max(10) String numberOfItems) throws ProductNotFoundException {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(numberOfItems));
        Map<String, Object> result = productService.findProductByName(name, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);


    }
    @PostMapping("/addReview/{id}")
    public ResponseEntity<?> addReview(@PathVariable String  id, @RequestBody String review) throws ProductNotFoundException {
      var response =  productService.addReview(Long.parseLong(id), review);

      return  new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/allReviews/{id}")
    public ResponseEntity<?> getAllReview(@PathVariable String id) throws ProductNotFoundException {
        var response = productService.getAllReviews(Long.parseLong(id));
        System.out.println("this is the size: " + response.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
