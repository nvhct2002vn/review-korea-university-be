package com.example.reviewkoreauniversitybe.service.impl;

import com.example.reviewkoreauniversitybe.dto.ReviewDTO;
import com.example.reviewkoreauniversitybe.model.Review;
import com.example.reviewkoreauniversitybe.model.ReviewCon;
import com.example.reviewkoreauniversitybe.model.ReviewPro;
import com.example.reviewkoreauniversitybe.model.University;
import com.example.reviewkoreauniversitybe.repository.ReviewRepository;
import com.example.reviewkoreauniversitybe.repository.UniversityRepository;
import com.example.reviewkoreauniversitybe.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private UniversityRepository universityRepository;
    
    @Override
    public ReviewDTO getReviewById(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
        
        return ReviewDTO.fromEntity(review);
    }
    
    @Override
    public Page<ReviewDTO> getReviewsByUniversityId(Integer universityId, Pageable pageable) {
        return reviewRepository.findByUniversityIdAndIsApprovedTrue(universityId, pageable)
                .map(ReviewDTO::fromEntity);
    }
    
    @Override
    @Transactional
    public ReviewDTO submitReview(ReviewDTO reviewDTO, Integer universityId, String ipAddress) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + universityId));
        
        Review review = new Review();
        review.setUniversity(university);
        review.setAuthor(reviewDTO.getAuthor());
        review.setRating(reviewDTO.getRating());
        review.setContent(reviewDTO.getContent());
        review.setProgramStudied(reviewDTO.getProgramStudied());
        review.setYearOfStudy(reviewDTO.getYearOfStudy());
        review.setIsInternational(reviewDTO.getIsInternational());
        review.setIsApproved(true); // Default to approved for public-facing API
        review.setIpAddress(ipAddress);
        review.setCreatedAt(LocalDateTime.now());
        
        // Add pros
        if (reviewDTO.getPros() != null && !reviewDTO.getPros().isEmpty()) {
            review.setPros(new ArrayList<>());
            for (String pro : reviewDTO.getPros()) {
                ReviewPro reviewPro = new ReviewPro();
                reviewPro.setReview(review);
                reviewPro.setContent(pro);
                reviewPro.setCreatedAt(LocalDateTime.now());
                review.getPros().add(reviewPro);
            }
        }
        
        // Add cons
        if (reviewDTO.getCons() != null && !reviewDTO.getCons().isEmpty()) {
            review.setCons(new ArrayList<>());
            for (String con : reviewDTO.getCons()) {
                ReviewCon reviewCon = new ReviewCon();
                reviewCon.setReview(review);
                reviewCon.setContent(con);
                reviewCon.setCreatedAt(LocalDateTime.now());
                review.getCons().add(reviewCon);
            }
        }
        
        Review savedReview = reviewRepository.save(review);
        
        // Update university average rating
        Double averageRating = reviewRepository.findAverageRatingByUniversityId(universityId);
        if (averageRating != null) {
            university.setAverageRating(java.math.BigDecimal.valueOf(averageRating));
            universityRepository.save(university);
        }
        
        return ReviewDTO.fromEntity(savedReview);
    }
} 