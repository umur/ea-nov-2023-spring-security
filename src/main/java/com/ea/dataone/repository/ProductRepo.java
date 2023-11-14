package com.ea.dataone.repository;

import com.ea.dataone.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends ListCrudRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThan(int minPrice);

    List<Product> findAllByNameContains(String keyword);

    @Query("select p from Product p where p.name=:name")
    List<Product> findAllByCriteria(@Param("name") String name);

}
