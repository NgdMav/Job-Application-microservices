package com.mav.microservicejob.job;

import com.mav.microservicejob.dto.JobWithCompanyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public ResponseEntity<List<JobWithCompanyDTO>> getAll() {
        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getOneById(id));
    }

    @PostMapping("/")
    public ResponseEntity<JobWithCompanyDTO> create(@Valid @RequestBody Job job) {
        return ResponseEntity.ok(jobService.create(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> update(@PathVariable Long id, @Valid @RequestBody Job job) {
        return ResponseEntity.ok(jobService.update(id, job));
    }

    @GetMapping("/{id}/company")
    public ResponseEntity<Long> getJobCompany(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobCompany(id));
    }
}
