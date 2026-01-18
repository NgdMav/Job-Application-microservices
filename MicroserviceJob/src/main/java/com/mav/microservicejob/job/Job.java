package com.mav.microservicejob.job;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;

public record Job(
        @Null
        Long id,
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        @PositiveOrZero
        Double minSalary,
        @NotNull
        @PositiveOrZero
        Double maxSalary,
        @NotNull
        String location,
        @NotNull
        Long companyId
) {
}
