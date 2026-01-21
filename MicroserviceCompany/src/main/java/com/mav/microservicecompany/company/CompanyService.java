package com.mav.microservicecompany.company;

import com.mav.microservicecompany.company.dto.ReviewMessage;
import jakarta.validation.Valid;

import java.util.List;

public interface CompanyService {

    List<Company> getAll();

    Company getOneById(Long id);

    Company create(@Valid Company company);

    void delete(Long id);

    Company update(Long id, @Valid Company company);
    void updateRating(ReviewMessage reviewMessage);
}
