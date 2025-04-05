package com.example.reviewkoreauniversitybe.service;

import com.example.reviewkoreauniversitybe.dto.UniversityDTO;
import com.example.reviewkoreauniversitybe.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UniversityService {
    
    // Get university by id
    UniversityDTO getUniversityById(Integer id);
    
    // Get all universities with pagination
    Page<UniversityDTO> getAllUniversities(Pageable pageable);
    
    // Get top rated universities
    Page<UniversityDTO> getTopRatedUniversities(Pageable pageable);
    
    // Find universities by name
    List<UniversityDTO> findUniversitiesByName(String name);
    
    // Find universities by location
    List<UniversityDTO> findUniversitiesByLocation(String location);
    
    // Find universities by type
    List<UniversityDTO> findUniversitiesByType(String type);
    
    // Get all unique locations
    List<String> getAllLocations();
    
    // Get all university types
    List<String> getAllTypes();
    
    // Get university images by university id
    List<String> getUniversityImages(Integer universityId);
} 