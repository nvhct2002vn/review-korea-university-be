package com.example.reviewkoreauniversitybe.service;

import com.example.reviewkoreauniversitybe.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    
    // Get review by id
    ReviewDTO getReviewById(Integer id);
    
    // Get reviews by university id
    Page<ReviewDTO> getReviewsByUniversityId(Integer universityId, Pageable pageable);
    
    // Submit a new review
    ReviewDTO submitReview(ReviewDTO reviewDTO, Integer universityId, String ipAddress);
} 