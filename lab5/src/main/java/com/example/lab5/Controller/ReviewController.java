package com.example.lab5.Controller;

import com.example.lab5.Model.Review;
import com.example.lab5.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id)
    {
        return reviewService.findById(id);
    }
    @PostMapping
    public Review saveUser(@RequestBody Review review){
        return reviewService.saveReview(review);
    }
   /* @PutMapping("/{id}")
    public Review updateReview(@RequestBody Review review,@PathVariable int id){
        return reviewService.updateReview(id,review);
    }*/
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id){
        reviewService.deleteReview(id);
    }

    @GetMapping("/product/{id}")
    public List<Review> getReviewsByProductId(@PathVariable int id){
        return reviewService.findAllByProductId(id);
    }
}
