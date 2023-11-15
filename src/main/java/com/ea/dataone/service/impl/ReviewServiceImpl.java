package com.ea.dataone.service.impl;

import com.ea.dataone.dto.ReviewDto;
import com.ea.dataone.service.ReviewService;
import com.ea.dataone.entity.Review;
import com.ea.dataone.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ModelMapper modelMapper;

    private final ReviewRepo reviewRepo;

    public void create(ReviewDto review) {
        reviewRepo.save(modelMapper.map(review, Review.class));
    }

    public List<ReviewDto> findAll() {
        return reviewRepo.findAll().stream()
                .map(review ->modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ReviewDto review) {
        reviewRepo.save(modelMapper.map(review, Review.class));
    }

    @Override
    public ReviewDto getReview(Long id) {
        Review review = reviewRepo.findById(id).orElse(null);
        if(review == null) return null;
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public void delete(ReviewDto review) {
        reviewRepo.delete(modelMapper.map(review, Review.class));
    }
}
