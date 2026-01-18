package com.mav.microservicereview.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByCompanyId(Long companyId);

    Optional<ReviewEntity> findByCompanyIdAndId(Long id, Long id1);

}