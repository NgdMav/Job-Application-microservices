package com.mav.microservicecompany.company.dto;

public record ReviewMessage(
        Long id,
        String title,
        String description,
        double rating,
        Long companyId
) {}
