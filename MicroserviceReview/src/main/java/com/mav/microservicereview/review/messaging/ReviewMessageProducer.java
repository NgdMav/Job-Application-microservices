package com.mav.microservicereview.review.messaging;

import com.mav.microservicereview.review.Review;
import com.mav.microservicereview.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendReviewMessage(Long companyId, Review review) {
        ReviewMessage reviewMessage = new ReviewMessage(
                review.id(),
                review.title(),
                review.description(),
                review.rating(),
                companyId
        );
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
