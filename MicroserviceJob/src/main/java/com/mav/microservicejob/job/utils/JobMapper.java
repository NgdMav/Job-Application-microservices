package com.mav.microservicejob.job.utils;

import com.mav.microservicejob.job.clients.CompanyClient;
import com.mav.microservicejob.job.clients.ReviewClient;
import com.mav.microservicejob.job.dto.JobDTO;
import com.mav.microservicejob.job.Job;
import com.mav.microservicejob.job.JobEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    public JobMapper(CompanyClient companyClient, ReviewClient reviewClient) {
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    public Job toDomain(JobEntity jobEntity) {
        return new Job(
                jobEntity.getId(),
                jobEntity.getTitle(),
                jobEntity.getDescription(),
                jobEntity.getMinSalary(),
                jobEntity.getMaxSalary(),
                jobEntity.getLocation(),
                jobEntity.getCompanyId()
        );
    }

    public JobEntity toEntity(Job job) {
        if (job == null) return null;

        return new JobEntity(
                job.id(),
                job.title(),
                job.description(),
                job.minSalary(),
                job.maxSalary(),
                job.location(),
                job.companyId()
        );
    }

    public JobDTO toDTO(JobEntity jobEntity) {
        Job job = toDomain(jobEntity);
        var company = companyClient.getCompany(job.companyId());
        if (company == null) throw new EntityNotFoundException("Company with id " + job.companyId() + " not found");
        var companyReviews = reviewClient.getReviews(job.companyId());
        return new JobDTO(
                job.id(),
                job.title(),
                job.description(),
                job.minSalary(),
                job.maxSalary(),
                job.location(),
                company,
                companyReviews
        );
    }
}
