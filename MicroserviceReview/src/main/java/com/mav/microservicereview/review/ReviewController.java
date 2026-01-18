package com.mav.microservicereview.review;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
class ReviewController {

    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Review>> getAll(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAll(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getOneById(@PathVariable Long companyId, @PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getOneById(companyId, id));
    }

    @PostMapping("/")
    public ResponseEntity<Review> create(@PathVariable Long companyId, @Valid @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.create(companyId, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long companyId, @PathVariable Long id) {
        reviewService.delete(companyId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long companyId, @PathVariable Long id, @Valid @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.update(companyId, id, review));
    }
}
