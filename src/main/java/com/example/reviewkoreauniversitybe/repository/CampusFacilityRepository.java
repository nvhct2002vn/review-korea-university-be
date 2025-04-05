package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.CampusFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusFacilityRepository extends JpaRepository<CampusFacility, Integer> {
    
    // Find facilities by university id
    List<CampusFacility> findByUniversityId(Integer universityId);
} 