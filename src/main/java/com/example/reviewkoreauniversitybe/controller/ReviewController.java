package com.example.reviewkoreauniversitybe.controller;

import com.example.reviewkoreauniversitybe.dto.ApiResponse;
import com.example.reviewkoreauniversitybe.dto.PageResponseDTO;
import com.example.reviewkoreauniversitybe.dto.ReviewDTO;
import com.example.reviewkoreauniversitybe.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*") // Allow from all origins for now
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewDTO>> getReviewById(@PathVariable Integer id) {
        try {
            ReviewDTO reviewDTO = reviewService.getReviewById(id);
            return ResponseEntity.ok(ApiResponse.success("Review with ID " + id + " retrieved", reviewDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/university/{universityId}")
    public ResponseEntity<ApiResponse<PageResponseDTO<ReviewDTO>>> getReviewsByUniversityId(
            @PathVariable Integer universityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {
        
        try {
            Sort sort = direction.equalsIgnoreCase("ASC") ? 
                    Sort.by(sortBy).ascending() : 
                    Sort.by(sortBy).descending();
            
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<ReviewDTO> reviewPage = reviewService.getReviewsByUniversityId(universityId, pageable);
            
            return ResponseEntity.ok(ApiResponse.success(
                    "Reviews for university with ID " + universityId + " retrieved",
                    PageResponseDTO.of(reviewPage)));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewDTO>> submitReview(
            @RequestBody @Validated ReviewDTO reviewDTO,
            HttpServletRequest request) {
        
        try {
            String ipAddress = request.getRemoteAddr();
            ReviewDTO savedReview = reviewService.submitReview(reviewDTO, reviewDTO.getUniversityId(), ipAddress);
            
            return ResponseEntity.ok(ApiResponse.success("Review submitted successfully", savedReview));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
} 