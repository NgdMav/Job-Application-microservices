package com.mav.microservicecompany.company;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record Company(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String description
) {
}
