package com.example.lab5.Repository;

import com.example.lab5.Model.Review;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends ListCrudRepository<Review,Integer> {
    Review findById(int id);

   // Review updateById(int id,Review review);
    void deleteById(int id);
    List<Review> findAllByProductId(int id);
}
