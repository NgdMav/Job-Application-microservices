package com.mav.microservicejob.dto;

import com.mav.microservicejob.external.Company;

public record JobDTO(
        Long id,
        String title,
        String description,
        Double minSalary,
        Double maxSalary,
        String location,
        Company company
) {
}
