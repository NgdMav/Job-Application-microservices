package com.mav.microservicereview.review.impl;

import com.mav.microservicereview.review.Review;
import com.mav.microservicereview.review.ReviewRepository;
import com.mav.microservicereview.review.ReviewService;
import com.mav.microservicereview.review.utils.ReviewMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<Review> getAll(Long companyId) {
        var reviews = reviewRepository.findByCompanyId(companyId);
        if (reviews == null || reviews.isEmpty()) {
            return Collections.emptyList();
        }
        return reviews.stream().map(reviewMapper::toDomain).toList();
    }

    @Override
    public Review getOneById(Long id) {
        return reviewMapper.toDomain(reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id + " not found")
        ));
    }

    @Override
    public Review create(Long companyId, Review review) {
        var validReview = reviewMapper.insertCompanyId(review, companyId);
        var reviewEntity = reviewMapper.toEntity(validReview);
        var result = reviewRepository.save(reviewEntity);
        return reviewMapper.toDomain(result);
    }

    @Override
    public void delete(Long id) {
        var reviewEntity = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id  + " not found")
        );
        reviewRepository.delete(reviewEntity);
    }

    @Override
    public Review update(Long id, Review review) {
        var reviewEntityOld = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id + " not found")
        );
        var validReview = reviewMapper.insertCompanyId(review, reviewEntityOld.getCompanyId());
        var reviewEntityNew = reviewMapper.toEntity(validReview);
        reviewEntityNew.setId(reviewEntityOld.getId());
        var result = reviewRepository.save(reviewEntityNew);
        return reviewMapper.toDomain(result);
    }
}
