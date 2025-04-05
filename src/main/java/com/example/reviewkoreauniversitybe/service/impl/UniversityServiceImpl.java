package com.example.reviewkoreauniversitybe.service.impl;

import com.example.reviewkoreauniversitybe.dto.TuitionFeeDTO;
import com.example.reviewkoreauniversitybe.dto.UniversityDTO;
import com.example.reviewkoreauniversitybe.model.University;
import com.example.reviewkoreauniversitybe.repository.*;
import com.example.reviewkoreauniversitybe.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {
    
    @Autowired
    private UniversityRepository universityRepository;
    
    @Autowired
    private UniversityImageRepository universityImageRepository;
    
    @Autowired
    private UniversityDepartmentRepository universityDepartmentRepository;
    
    @Autowired
    private CampusFacilityRepository campusFacilityRepository;
    
    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Override
    public UniversityDTO getUniversityById(Integer id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + id));
        
        return enrichUniversityDTO(UniversityDTO.fromEntity(university));
    }
    
    @Override
    public Page<UniversityDTO> getAllUniversities(Pageable pageable) {
        return universityRepository.findByIsActiveTrue(pageable)
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO);
    }
    
    @Override
    public Page<UniversityDTO> getTopRatedUniversities(Pageable pageable) {
        return universityRepository.findTopRatedUniversities(pageable)
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO);
    }
    
    @Override
    public List<UniversityDTO> findUniversitiesByName(String name) {
        return universityRepository.findByNameContainingIgnoreCaseOrNameKoreanContainingIgnoreCase(name, name)
                .stream()
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<UniversityDTO> findUniversitiesByLocation(String location) {
        return universityRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<UniversityDTO> findUniversitiesByType(String type) {
        try {
            University.UniversityType universityType = University.UniversityType.valueOf(type);
            return universityRepository.findByType(universityType)
                    .stream()
                    .map(UniversityDTO::fromEntity)
                    .map(this::enrichUniversityDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid university type: " + type);
        }
    }
    
    @Override
    public List<String> getAllLocations() {
        return universityRepository.findAllLocations();
    }
    
    @Override
    public List<String> getAllTypes() {
        return Arrays.stream(University.UniversityType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> getUniversityImages(Integer universityId) {
        // Check if university exists
        if (!universityRepository.existsById(universityId)) {
            throw new EntityNotFoundException("University not found with id: " + universityId);
        }
        
        // Get images
        List<String> imageUrls = new ArrayList<>();
        universityImageRepository.findByUniversityIdOrderByDisplayOrderAsc(universityId)
                .forEach(image -> imageUrls.add(image.getImageUrl()));
        
        return imageUrls;
    }
    
    private UniversityDTO enrichUniversityDTO(UniversityDTO universityDTO) {
        // Get images
        List<String> imageUrls = new ArrayList<>();
        universityImageRepository.findByUniversityIdOrderByDisplayOrderAsc(universityDTO.getId())
                .forEach(image -> imageUrls.add(image.getImageUrl()));
        universityDTO.setImages(imageUrls);
        
        // Get departments
        List<String> departments = new ArrayList<>();
        universityDepartmentRepository.findByUniversityId(universityDTO.getId())
                .forEach(dept -> departments.add(dept.getName()));
        universityDTO.setDepartments(departments);
        
        // Get facilities
        List<String> facilities = new ArrayList<>();
        campusFacilityRepository.findByUniversityId(universityDTO.getId())
                .forEach(facility -> facilities.add(facility.getFacilityName()));
        universityDTO.setFacilities(facilities);
        
        // Get tuition fee
        universityDTO.setTuition(
                tuitionFeeRepository.findByUniversityId(universityDTO.getId())
                        .map(TuitionFeeDTO::fromEntity)
                        .orElse(null)
        );
        
        // Get review count
        universityDTO.setReviewCount(
                reviewRepository.countByUniversityIdAndIsApprovedTrue(universityDTO.getId())
        );
        
        return universityDTO;
    }
} 