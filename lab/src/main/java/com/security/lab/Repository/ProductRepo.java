package com.security.lab.Repository;

import com.security.lab.Entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends ListCrudRepository<Product, Integer> {
    Optional<Product> findById(Integer id);

}
