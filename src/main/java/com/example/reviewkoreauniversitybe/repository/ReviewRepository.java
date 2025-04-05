package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
    // Find reviews by university id
    Page<Review> findByUniversityIdAndIsApprovedTrue(Integer universityId, Pageable pageable);
    
    // Find average rating for a university
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.university.id = :universityId AND r.isApproved = true")
    Double findAverageRatingByUniversityId(@Param("universityId") Integer universityId);
    
    // Count reviews for a university
    Long countByUniversityIdAndIsApprovedTrue(Integer universityId);
} 