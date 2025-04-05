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

/**
 * Controller xử lý các API liên quan đến trường đại học
 * Cung cấp các endpoint để truy vấn và tìm kiếm thông tin về trường đại học
 */
@RestController
@RequestMapping("/universities")
@CrossOrigin(origins = "*") // Cho phép truy cập từ tất cả các nguồn (CORS)
public class UniversityController {
    
    @Autowired
    private UniversityService universityService;
    
    /**
     * API lấy danh sách tất cả các trường đại học có phân trang và hỗ trợ lọc
     * 
     * @param page - Số trang (mặc định: 0)
     * @param size - Số lượng kết quả trên một trang (mặc định: 10)
     * @param sortBy - Tiêu chí sắp xếp (mặc định: "name")
     * @param location - Lọc theo địa điểm (tùy chọn)
     * @param type - Lọc theo loại trường (Public/Private) (tùy chọn)
     * @param search - Tìm kiếm theo tên (tùy chọn)
     * @return Danh sách các trường đại học theo điều kiện lọc
     */
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
            
            // Nếu có tham số location, lọc theo địa điểm
            if (location != null && !location.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByLocation(location);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities filtered by location: " + location, result));
            }
            
            // Nếu có tham số type, lọc theo loại trường
            if (type != null && !type.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByType(type);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities filtered by type: " + type, result));
            }
            
            // Nếu có tham số search, tìm kiếm theo tên
            if (search != null && !search.isEmpty()) {
                List<UniversityDTO> universities = universityService.findUniversitiesByName(search);
                result = PageResponseDTO.of(universities, page, size);
                return ResponseEntity.ok(ApiResponse.success("Universities matching search: " + search, result));
            }
            
            // Trường hợp mặc định - trả về tất cả trường đại học có phân trang
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<UniversityDTO> universityPage = universityService.getAllUniversities(pageable);
            result = PageResponseDTO.of(universityPage);
            return ResponseEntity.ok(ApiResponse.success("All universities retrieved", result));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
    
    /**
     * API lấy danh sách các trường đại học xếp hạng cao nhất
     * 
     * @param page - Số trang (mặc định: 0)
     * @param size - Số lượng kết quả (mặc định: 3)
     * @return Danh sách các trường đại học được xếp hạng cao nhất
     */
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
    
    /**
     * API lấy thông tin chi tiết của một trường đại học theo ID
     * 
     * @param id - ID của trường đại học
     * @return Thông tin chi tiết của trường đại học
     */
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
    
    /**
     * API lấy danh sách ảnh của một trường đại học theo ID
     * 
     * @param id - ID của trường đại học
     * @return Danh sách các URL ảnh của trường đại học
     */
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
    
    /**
     * API tìm kiếm trường đại học theo tên
     * 
     * @param query - Từ khóa tìm kiếm
     * @return Danh sách các trường đại học khớp với từ khóa
     */
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
    
    /**
     * API lấy danh sách trường đại học theo địa điểm
     * 
     * @param location - Địa điểm cần lọc
     * @return Danh sách các trường đại học ở địa điểm được chỉ định
     */
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
    
    /**
     * API lấy danh sách trường đại học theo loại (Public/Private)
     * 
     * @param type - Loại trường (Public/Private)
     * @return Danh sách các trường đại học thuộc loại được chỉ định
     */
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
    
    /**
     * API lấy danh sách tất cả các địa điểm có trường đại học
     * 
     * @return Danh sách các địa điểm duy nhất
     */
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
    
    /**
     * API lấy danh sách tất cả các loại trường đại học
     * 
     * @return Danh sách các loại trường (Public/Private)
     */
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