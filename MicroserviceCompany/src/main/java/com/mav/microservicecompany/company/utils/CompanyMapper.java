package com.mav.microservicecompany.company.utils;

import com.mav.microservicecompany.company.Company;
import com.mav.microservicecompany.company.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toDomain(CompanyEntity companyEntity) {
        return new Company(
                companyEntity.getId(),
                companyEntity.getName(),
                companyEntity.getDescription(),
                companyEntity.getRating()
        );
    }

    public CompanyEntity toEntity(Company company) {
        return new CompanyEntity(
                company.id(),
                company.name(),
                company.description(),
                company.rating()
        );
    }
}
