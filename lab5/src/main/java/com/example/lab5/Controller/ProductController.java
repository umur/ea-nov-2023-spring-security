package com.example.lab5.Controller;


import com.example.lab5.Model.Category;
import com.example.lab5.Model.Product;
import com.example.lab5.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id)
    {
        return productService.findById(id);
    }
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
   /* @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        return productService.updateProduct(id,product);
    }*/
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
    @GetMapping("/products-gt-price/{price}")
    public List<Product> getProductsGreaterThanPrice(@PathVariable double price){
        return productService.getProductsMoreThan(price);
    }
@GetMapping( "/inCatAndLessmaxPrice/")
List<Product> findAllByCategoryAndPriceLessThan(@RequestParam Category category, @RequestParam double maxPrice){
        return productService.findAllByCategoryAndPriceLessThan(category,maxPrice);
}
@GetMapping("/ProductsNameContainsKeyword/{keyword}")
    public List<Product> getProductsNameContainsKeyword(@PathVariable String keyword){
        return productService.findAllByNameContainsKeyword(keyword);
}
}
