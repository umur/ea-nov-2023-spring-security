package com.example.lab5.Service;

import com.example.lab5.Model.Review;

import java.util.List;

public interface ReviewService {
    Review findById(int id);
    Review saveReview(Review review);
   // Review updateReview(int id,Review review);
    void deleteReview(int id);
    List<Review> findAllByProductId(int id);
}
