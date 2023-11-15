package com.example.lab5.Service;


import com.example.lab5.Model.Category;
import com.example.lab5.Model.Product;

import java.util.List;

public interface ProductService {
    Product findById(int id);
    Product saveProduct(Product product);
   // Product updateProduct(int id,Product product);
    void deleteProduct(int id);
    List<Product> getProductsMoreThan(double minPrice);
    List<Product> findAllByCategoryAndPriceLessThan(Category category, double price);
    List<Product> findAllByNameContainsKeyword(String keyword);

}

