package com.ea.dataone.controller;

import com.ea.dataone.aop.annotation.ExecutionTime;
import com.ea.dataone.dto.CategoryDto;
import com.ea.dataone.dto.ProductDto;
import com.ea.dataone.dto.ReviewDto;
import com.ea.dataone.service.CategoryService;
import com.ea.dataone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductService reviewService;

    @ExecutionTime
    @GetMapping("/findAllByPriceGreaterThan")
    public List<ProductDto> findAllByPriceGreaterThan(@RequestParam int minPrice) {
        return productService.findAllByPriceGreaterThan(minPrice);
    }

    @ExecutionTime
    @GetMapping("/findAllProductsInCatCategoryAndLessThanMaxPrice")
    public List<ProductDto> findAllByNameContains(@RequestParam String cat, @RequestParam int maxPrice) {
        CategoryDto category = categoryService.findByName(cat);
        return category.getProducts().stream().filter(p -> p.getPrice() < maxPrice).collect(Collectors.toList());
    }

    @ExecutionTime
    @GetMapping("/findAllByNameContains")
    public List<ProductDto> findAllByNameContains(@RequestParam String keyword) {
        return productService.findAllByNameContains(keyword);
    }

    @ExecutionTime
    @GetMapping("/findAllReviewsByProductId")
    public List<ReviewDto> findAllByNameContains(@RequestParam Long id) {
        ProductDto product = productService.getProduct(id);
        return product.getReviews();
    }


}