package com.mav.microservicereview.review.dto;

public record ReviewMessage(
        Long id,
        String title,
        String description,
        double rating,
        Long companyId
) {}
