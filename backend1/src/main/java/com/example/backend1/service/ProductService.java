package com.example.backend1.service;


import com.example.backend1.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductsById(int id);

    void deleteProductById(int id);

    Product updateProductById(int id, Product product);



}
