package com.mav.microservicejob.dto;

import com.mav.microservicejob.external.Company;
import com.mav.microservicejob.job.Job;

public record JobWithCompanyDTO(
        Job job,
        Company company
) {
}
