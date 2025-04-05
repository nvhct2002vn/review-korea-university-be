package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    
    // Find universities by location
    List<University> findByLocationContainingIgnoreCase(String location);
    
    // Find universities by type
    List<University> findByType(University.UniversityType type);
    
    // Find universities by name
    List<University> findByNameContainingIgnoreCaseOrNameKoreanContainingIgnoreCase(String name, String nameKorean);
    
    // Find top rated universities
    @Query("SELECT u FROM University u WHERE u.isActive = true ORDER BY u.averageRating DESC, u.ranking ASC")
    Page<University> findTopRatedUniversities(Pageable pageable);
    
    // Find all active universities
    Page<University> findByIsActiveTrue(Pageable pageable);
    
    // Find all locations (distinct)
    @Query("SELECT DISTINCT u.location FROM University u WHERE u.isActive = true ORDER BY u.location")
    List<String> findAllLocations();
} 