package com.ea.dataone.service;

import com.ea.dataone.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void create(ReviewDto review);

    List<ReviewDto> findAll();

    void update(ReviewDto review);

    ReviewDto getReview(Long id);

    void delete(ReviewDto review);
}
