package com.mav.microservicejob.job;

import com.mav.microservicejob.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> getAll();

    JobDTO getOneById(Long id);

    JobDTO create(Job job);

    void delete(Long id);

    JobDTO update(Long id, Job job);

    Long getJobCompany(Long id);
}
