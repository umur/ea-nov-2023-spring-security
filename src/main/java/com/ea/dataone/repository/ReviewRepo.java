package com.ea.dataone.repository;

import com.ea.dataone.entity.Review;
import org.springframework.data.repository.ListCrudRepository;

public interface ReviewRepo extends ListCrudRepository<Review, Long> {
}
