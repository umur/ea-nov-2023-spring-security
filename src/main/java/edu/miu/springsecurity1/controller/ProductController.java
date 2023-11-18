package edu.miu.springsecurity1.controller;


import edu.miu.springsecurity1.entity.Product;
import edu.miu.springsecurity1.entity.Review;
import edu.miu.springsecurity1.entity.dto.request.ProductDtoRequest;
import edu.miu.springsecurity1.entity.dto.response.ProductDtoResponse;
import edu.miu.springsecurity1.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDtoRequest p) {
        return ResponseEntity.ok(productService.save(p));

    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_CLIENT')") it is not important because overwriten by security config
    public List<ProductDtoResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getById(@PathVariable int id) {
        var product = productService.getById(id);
        return ResponseEntity.ok(modelMapper.map(product, ProductDtoResponse.class));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int productId) {
        //call service
    }
}
