package africa.semicolon.ecommerce.controller;

import africa.semicolon.ecommerce.data.model.Product;
import africa.semicolon.ecommerce.dto.requests.AddProductRequest;
import africa.semicolon.ecommerce.dto.requests.UpdateProductRequest;
import africa.semicolon.ecommerce.exceptions.ProductNotFoundException;
import africa.semicolon.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN, SELLER, ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest) {
        var response = productService.addProduct(addProductRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/findById/{id}")
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
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        var response = productService.deleteProductById(Long.parseLong(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findProductByName(@RequestParam String name,
                                               @RequestParam(defaultValue = "1",value = "pageNo", required = false) String pageNo,
                                               @RequestParam(defaultValue = "10",value = "pageSize", required = false) String pageSize) throws ProductNotFoundException {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        Map<String, Object> result = productService.findProductByName(name, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);


    }

}
