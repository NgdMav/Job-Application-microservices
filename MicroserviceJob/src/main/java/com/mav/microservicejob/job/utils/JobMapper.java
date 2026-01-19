package com.mav.microservicejob.job.utils;

import com.mav.microservicejob.dto.JobDTO;
import com.mav.microservicejob.external.Company;
import com.mav.microservicejob.external.Review;
import com.mav.microservicejob.job.Job;
import com.mav.microservicejob.job.JobEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class JobMapper {

    RestTemplate restTemplate;

    public JobMapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        var company = restTemplate.getForObject("http://MICROSERVICECOMPANY:8081/companies/" + job.companyId(), Company.class);
        if (company == null) throw new EntityNotFoundException("Company with id " + job.companyId() + " not found");
        var companyReviewsResponse = restTemplate.exchange(
                "http://MICROSERVICEREVIEW:8083/reviews/?companyId=" +  job.companyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<Review>>() {}
        );
        var companyReviews = companyReviewsResponse.getBody();
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
