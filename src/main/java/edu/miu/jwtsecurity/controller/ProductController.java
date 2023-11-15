package edu.miu.jwtsecurity.controller;

import edu.miu.jwtsecurity.dto.ProductDto;
import edu.miu.jwtsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final  ProductService productService;
    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) throws Exception {
       return productService.addProduct(productDto);
    }
}
