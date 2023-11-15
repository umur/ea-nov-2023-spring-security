package com.ea.dataone.controller;

import com.ea.dataone.aop.annotation.ExecutionTime;
import com.ea.dataone.dto.ProductDto;
import com.ea.dataone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@RequestBody ProductDto product) {
        productService.create(product);
    }

    @ExecutionTime
    @GetMapping
    public List<ProductDto> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping
    public void update(@RequestBody ProductDto product) {
        productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ProductDto product = productService.getProduct(id);
        productService.delete(product);
    }
}