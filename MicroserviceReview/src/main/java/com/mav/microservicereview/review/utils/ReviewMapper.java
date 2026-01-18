package com.mav.microservicereview.review.utils;

import com.mav.microservicereview.review.Review;
import com.mav.microservicereview.review.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review toDomain(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getId(),
                reviewEntity.getTitle(),
                reviewEntity.getDescription(),
                reviewEntity.getRating(),
                reviewEntity.getCompanyId()
        );
    }

    public ReviewEntity toEntity(Review review) {
        return new ReviewEntity(
                review.id(),
                review.title(),
                review.description(),
                review.rating(),
                review.companyId()
        );
    }

    public Review insertCompanyId(Review review, Long companyId) {
        return new Review(
                review.id(),
                review.title(),
                review.description(),
                review.rating(),
                companyId
        );
    }
}
