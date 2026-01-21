package com.mav.microservicecompany.company.messaging;

import com.mav.microservicecompany.company.CompanyService;
import com.mav.microservicecompany.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateRating(reviewMessage);
    }
}
