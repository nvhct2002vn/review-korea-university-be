package com.example.reviewkoreauniversitybe.controller;

import com.example.reviewkoreauniversitybe.dto.ApiResponse;
import com.example.reviewkoreauniversitybe.dto.PageResponseDTO;
import com.example.reviewkoreauniversitybe.dto.UniversityDTO;
import com.example.reviewkoreauniversitybe.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@CrossOrigin(origins = "*") // Allow from all origins for now
public class UniversityController {
    
    @Autowired
    private UniversityService universityService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDTO<UniversityDTO>>> getAllUniversities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search) {
        
        try {
            PageResponseDTO<UniversityDTO> result;
            
            // If location parameter is provided
            if (location != null && !location.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByLocation(location);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities filtered by location: " + location, result));
            }
            
            // If type parameter is provided
            if (type != null && !type.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByType(type);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities filtered by type: " + type, result));
            }
            
            // If search parameter is provided
            if (search != null && !search.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByName(search);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities matching search: " + search, result));
            }
            
            // Default case - return all universities
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<UniversityDTO> universityPage = universityService.getAllUniversities(pageable);
            result = PageResponseDTO.of(universityPage);
            return ResponseEntity.ok(ApiResponse.success("All universities retrieved", result));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/top")
    public ResponseEntity<ApiResponse<PageResponseDTO<UniversityDTO>>> getTopUniversities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<UniversityDTO> universityPage = universityService.getTopRatedUniversities(pageable);
            
            return ResponseEntity.ok(ApiResponse.success(
                    "Top " + size + " universities retrieved", 
                    PageResponseDTO.of(universityPage)));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UniversityDTO>> getUniversityById(@PathVariable Integer id) {
        try {
            UniversityDTO universityDTO = universityService.getUniversityById(id);
            return ResponseEntity.ok(ApiResponse.success(
                    "University with ID " + id + " retrieved", 
                    universityDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/images")
    public ResponseEntity<ApiResponse<List<String>>> getUniversityImages(@PathVariable Integer id) {
        try {
            List<String> images = universityService.getUniversityImages(id);
            return ResponseEntity.ok(ApiResponse.success(
                    "Images for university with ID " + id + " retrieved", 
                    images));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UniversityDTO>>> searchUniversities(@RequestParam String query) {
        try {
            List<UniversityDTO> universities = universityService.findUniversitiesByName(query);
            return ResponseEntity.ok(ApiResponse.success(
                    "Universities matching '" + query + "' retrieved", 
                    universities));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/by-location")
    public ResponseEntity<ApiResponse<List<UniversityDTO>>> getUniversitiesByLocation(@RequestParam String location) {
        try {
            List<UniversityDTO> universities = universityService.findUniversitiesByLocation(location);
            return ResponseEntity.ok(ApiResponse.success(
                    "Universities in " + location + " retrieved", 
                    universities));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/by-type")
    public ResponseEntity<ApiResponse<List<UniversityDTO>>> getUniversitiesByType(@RequestParam String type) {
        try {
            List<UniversityDTO> universities = universityService.findUniversitiesByType(type);
            return ResponseEntity.ok(ApiResponse.success(
                    type + " universities retrieved", 
                    universities));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/locations")
    public ResponseEntity<ApiResponse<List<String>>> getAllLocations() {
        try {
            List<String> locations = universityService.getAllLocations();
            return ResponseEntity.ok(ApiResponse.success(
                    "All university locations retrieved", 
                    locations));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/types")
    public ResponseEntity<ApiResponse<List<String>>> getAllTypes() {
        try {
            List<String> types = universityService.getAllTypes();
            return ResponseEntity.ok(ApiResponse.success(
                    "All university types retrieved", 
                    types));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
} 