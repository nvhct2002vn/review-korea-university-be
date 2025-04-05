package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.UniversityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityImageRepository extends JpaRepository<UniversityImage, Integer> {
    
    // Find images by university id
    List<UniversityImage> findByUniversityIdOrderByDisplayOrderAsc(Integer universityId);
} 