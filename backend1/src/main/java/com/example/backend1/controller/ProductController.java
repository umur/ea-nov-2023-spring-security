package com.example.backend1.controller;

import com.example.backend1.model.Product;
import com.example.backend1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    //create Products
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        this.productService.saveProduct(product);
        return product;
    }

    //read Products
    @GetMapping
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductsById(@PathVariable int id){
        return this.productService.getProductsById(id);
    }


    //delete Product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProductById(id);
    }

    //update Product by Id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return productService.updateProductById(id, product);
    }




}

