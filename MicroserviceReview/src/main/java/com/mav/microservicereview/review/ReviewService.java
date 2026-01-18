package com.mav.microservicereview.review;

import jakarta.validation.Valid;

import java.util.List;

public interface ReviewService {

    List<Review> getAll(Long companyId);

    Review getOneById(Long id);

    Review create(Long companyId, @Valid Review review);

    void delete(Long id);

    Review update(Long id, @Valid Review review);
}
