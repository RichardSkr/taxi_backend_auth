package com.taxireview.api.services.impl;

import com.taxireview.api.dto.ReviewDto;
import com.taxireview.api.exceptions.DriverNotFoundException;
import com.taxireview.api.exceptions.ReviewNotFoundException;
import com.taxireview.api.models.Driver;
import com.taxireview.api.models.Review;
import com.taxireview.api.repositories.DriverRepository;
import com.taxireview.api.repositories.ReviewRepository;
import com.taxireview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private DriverRepository driverRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public ReviewDto createReview(long driverId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException("Driver not found."));

        review.setDriver(driver);

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewByDriverId(long id) {
        List<Review> reviews = reviewRepository.findByDriverId(id);

        return reviews.stream()
                .map(review -> mapToDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(long driverId, long reviewId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException("Driver with associated review not found."));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated driver not found."));

        if(review.getDriver().getId() != driver.getId()) {
            throw new ReviewNotFoundException("This review doesnt belong to driver.");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(long reviewId, long driverId, ReviewDto reviewDto) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException("Driver with associated review not found."));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated driver not found."));

        if(review.getDriver().getId() != driver.getId()) {
            throw new ReviewNotFoundException("This review doesnt belong to driver.");
        }

        review.setReview(reviewDto.getReview());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(long reviewId, long driveId) {

    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReview(review.getReview());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setReview(reviewDto.getReview());
        return review;
    }
}
