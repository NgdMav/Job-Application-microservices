package com.mav.microservicereview.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Range;

public record Review(
        @Null
        Long id,
        @NotNull
        String title,
        @NotNull
        String description,
        @Range(min = 1, max = 100)
        @NotNull
        double rating,
        @Null
        Long companyId
) {
}
