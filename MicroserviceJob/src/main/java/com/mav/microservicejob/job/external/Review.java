package com.mav.microservicejob.job.external;

public record Review(
        Long id,
        String title,
        String description,
        double rating
) {
}
