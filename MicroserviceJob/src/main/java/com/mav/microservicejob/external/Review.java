package com.mav.microservicejob.external;

public record Review(
        Long id,
        String title,
        String description,
        double rating
) {
}
