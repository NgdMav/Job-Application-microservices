package com.mav.microservicejob.job.clients;

import com.mav.microservicejob.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "REVIEW-MICROSERVICE:8083",
        url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews/")
    Set<Review> getReviews(@RequestParam("companyId") Long companyId);
}
