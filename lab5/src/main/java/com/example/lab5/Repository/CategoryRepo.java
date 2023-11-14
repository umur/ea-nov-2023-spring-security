package com.example.lab5.Repository;

import com.example.lab5.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findById(int id);

   // Category updateById(int id,Category category);
    void deleteById(int id);
}
