package com.taxireview.api.controllers;

import com.taxireview.api.dto.ReviewDto;
import com.taxireview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/driver/{driverId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "driverId") long driverId,
                                                  @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(driverId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/driver/{driverId}/reviews")
    public List<ReviewDto> getReviewsByDriverId(@PathVariable(value = "driverId") long driverId) {
        return reviewService.getReviewByDriverId(driverId);
    }

    @GetMapping("/driver/{driverId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "driverId") long driverId,
                                                   @PathVariable(value = "id") long reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(driverId, reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/driver/{driverId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "driverId") long driverId,
                                                  @PathVariable(value = "id") long reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updateReview = reviewService.updateReview(reviewId, driverId, reviewDto);
        return new ResponseEntity<>(updateReview,HttpStatus.OK);
    }
}
