package com.ea.dataone.controller;

import com.ea.dataone.dto.ReviewDto;
import com.ea.dataone.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public void create(@RequestBody ReviewDto review) {
        reviewService.create(review);
    }

    @GetMapping
    public List<ReviewDto> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReview(id);
    }

    @PutMapping
    public void update(@RequestBody ReviewDto review) {
        reviewService.update(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ReviewDto review = reviewService.getReview(id);
        reviewService.delete(review);
    }
}