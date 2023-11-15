package com.security.lab.Controller;

import com.security.lab.Dto.ProductDto;
import com.security.lab.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    @GetMapping("")
    public List<ProductDto> getProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductOne(@PathVariable Integer id) {
        return service.getOneProduct(id);
    }
}
