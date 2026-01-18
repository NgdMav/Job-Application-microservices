package com.mav.microservicejob.web;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        String description,
        LocalDateTime errorTime
) {
}
