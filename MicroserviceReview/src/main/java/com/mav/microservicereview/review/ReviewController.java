package com.mav.microservicereview.review;

import com.mav.microservicereview.review.messaging.ReviewMessageProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping("/")
    public ResponseEntity<List<Review>> getAll(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.getAll(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getOneById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Review> create(@RequestParam Long companyId, @Valid @RequestBody Review review) {
        var response = ResponseEntity.ok(reviewService.create(companyId, review));
        reviewMessageProducer.sendReviewMessage(companyId, review);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @Valid @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.update(id, review));
    }

    @GetMapping("/averageRating")
    public ResponseEntity<Double> getAverageRating(@RequestParam Long companyId) {
        var allReviews = reviewService.getAll(companyId);
        return ResponseEntity.ok(allReviews.stream().mapToDouble(Review::rating).average().orElse(0.0));
    }
}
