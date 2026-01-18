package com.mav.microservicejob.job;

import java.util.List;

public interface JobService {
    List<Job> getAll();

    Job getOneById(Long id);

    Job create(Job job);

    void delete(Long id);

    Job update(Long id, Job job);

    Long getJobCompany(Long id);
}
