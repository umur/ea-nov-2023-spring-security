package com.example.lab5.Service.Impl;

import com.example.lab5.Model.Review;
import com.example.lab5.Repository.ReviewRepo;
import com.example.lab5.Aspect.ExecutionTime;
import com.example.lab5.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private  final ReviewRepo reviewRepo;
    @Override
    public Review findById(int id) {
        return reviewRepo.findById(id);
    }
@ExecutionTime
    @Override
    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }

    /*@Override
    public Review updateReview(int id, Review review) {
        return reviewRepo.updateById(id,review);
    }*/

    @Override
    public void deleteReview(int id) {
        reviewRepo.deleteById(id);
    }

    @Override
    public List<Review> findAllByProductId(int id) {
        return reviewRepo.findAllByProductId(id);
    }
}
