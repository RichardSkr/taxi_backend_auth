package com.taxireview.api.services;

import com.taxireview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(long driverId, ReviewDto reviewDto);
    List<ReviewDto> getReviewByDriverId(long id);
    ReviewDto getReviewById(long reviewId, long driverId);
    ReviewDto updateReview(long reviewId, long driverId, ReviewDto reviewDto);
    void deleteReview(long reviewId, long driveId);
}
