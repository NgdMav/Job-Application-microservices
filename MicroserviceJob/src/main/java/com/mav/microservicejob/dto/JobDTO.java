package com.mav.microservicejob.dto;

import com.mav.microservicejob.external.Company;
import com.mav.microservicejob.external.Review;

import java.util.Set;

public record JobDTO(
        Long id,
        String title,
        String description,
        Double minSalary,
        Double maxSalary,
        String location,
        Company company,
        Set<Review> companyReviews
) {
}
