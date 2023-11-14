package com.ea.dataone.repository;

import com.ea.dataone.entity.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends ListCrudRepository<Category, Long> {
    Category findByName(String name);


}
