package com.mav.microservicecompany.company.impl;

import com.mav.microservicecompany.company.Company;
import com.mav.microservicecompany.company.CompanyRepository;
import com.mav.microservicecompany.company.CompanyService;
import com.mav.microservicecompany.company.utils.CompanyMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
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
}
