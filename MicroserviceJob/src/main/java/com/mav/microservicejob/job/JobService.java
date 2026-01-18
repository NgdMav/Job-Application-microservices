package com.mav.microservicejob.job;

import com.mav.microservicejob.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> getAll();

    JobWithCompanyDTO getOneById(Long id);

    JobWithCompanyDTO create(Job job);

    void delete(Long id);

    JobWithCompanyDTO update(Long id, Job job);

    Long getJobCompany(Long id);
}
