package edu.miu.ea.security.controller;

import edu.miu.ea.security.dto.ProductDTO;
import edu.miu.ea.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDto) throws Exception {
        ProductDTO productDTO = productService.addProduct(productDto);
        System.out.println("API Complete " + productDTO.getName());
        return productDTO;
    }
}
