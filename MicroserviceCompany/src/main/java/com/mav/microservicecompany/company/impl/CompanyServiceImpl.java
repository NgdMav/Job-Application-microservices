package com.mav.microservicecompany.company.impl;

import com.mav.microservicecompany.company.Company;
import com.mav.microservicecompany.company.CompanyRepository;
import com.mav.microservicecompany.company.CompanyService;
import com.mav.microservicecompany.company.clients.ReviewClient;
import com.mav.microservicecompany.company.dto.ReviewMessage;
import com.mav.microservicecompany.company.utils.CompanyMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll().stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public Company getOneById(Long id) {
        return companyMapper.toDomain(companyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Company with id " + id + " not found!")
        ));
    }

    @Override
    public Company create(Company company) {
        var companyEntity = companyMapper.toEntity(company);
        var result = companyRepository.save(companyEntity);
        return companyMapper.toDomain(result);
    }

    @Override
    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new EntityNotFoundException("Company with id " + id + " not found!");
        }
        companyRepository.deleteById(id);
    }

    @Override
    public Company update(Long id, Company company) {
        var companyEntityOld = companyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Company with id " + id + " not found!")
        );
        var companyEntityNew = companyMapper.toEntity(company);
        companyEntityNew.setId(companyEntityOld.getId());
        var result = companyRepository.save(companyEntityNew);
        return companyMapper.toDomain(result);
    }

    @Override
    public void updateRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.toString() + "\n" + reviewMessage.rating());
        var companyEntity = companyRepository.findById(reviewMessage.companyId()).orElseThrow(
                () -> new EntityNotFoundException("Company with id " + reviewMessage.companyId() + " not found!")
        );

        double rating = reviewClient.getAverageRating(reviewMessage.companyId());
        companyEntity.setRating(rating);
        companyRepository.save(companyEntity);
    }
}
