package com.mav.microservicejob.job.utils;

import com.mav.microservicejob.job.Job;
import com.mav.microservicejob.job.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
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
}
