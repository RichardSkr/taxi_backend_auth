package com.taxireview.api.repositories;

import com.taxireview.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByDriverId(long id);
}
