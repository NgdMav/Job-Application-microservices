package com.mav.microservicejob.job.dto;

import com.mav.microservicejob.job.external.Company;
import com.mav.microservicejob.job.external.Review;

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
