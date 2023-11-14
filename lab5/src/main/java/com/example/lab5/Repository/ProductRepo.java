package com.example.lab5.Repository;

import com.example.lab5.Model.Category;
import com.example.lab5.Model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends ListCrudRepository<Product,Integer> {
    Product findById(int id);

   // Product updateById(int id,Product product);
    void deleteById(int id);
    List<Product> findAllByPriceGreaterThan(double Price);
    List<Product> findAllByCategoryAndPriceLessThan(Category category, double price);
    List<Product> findAllByNameContains(String keyword);
}
