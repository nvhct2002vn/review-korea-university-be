package com.example.reviewkoreauniversitybe.service;

import com.example.reviewkoreauniversitybe.dto.UniversityDTO;
import com.example.reviewkoreauniversitybe.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface định nghĩa các phương thức dịch vụ liên quan đến trường đại học
 * Cung cấp các chức năng để truy vấn và tìm kiếm thông tin về trường đại học
 */
public interface UniversityService {
    
    /**
     * Lấy thông tin trường đại học theo ID
     * 
     * @param id - ID của trường đại học cần tìm
     * @return Thông tin đầy đủ của trường đại học dưới dạng DTO
     * @throws EntityNotFoundException nếu không tìm thấy trường đại học
     */
    UniversityDTO getUniversityById(Integer id);
    
    /**
     * Lấy danh sách tất cả các trường đại học có phân trang
     * 
     * @param pageable - Thông tin về phân trang và sắp xếp
     * @return Trang chứa danh sách các trường đại học
     */
    Page<UniversityDTO> getAllUniversities(Pageable pageable);
    
    /**
     * Lấy danh sách các trường đại học có xếp hạng cao nhất
     * 
     * @param pageable - Thông tin về phân trang và sắp xếp
     * @return Trang chứa danh sách các trường đại học được sắp xếp theo xếp hạng
     */
    Page<UniversityDTO> getTopRatedUniversities(Pageable pageable);
    
    /**
     * Tìm kiếm trường đại học theo tên (tiếng Anh hoặc tiếng Hàn)
     * 
     * @param name - Từ khóa tìm kiếm trong tên trường
     * @return Danh sách các trường đại học có tên khớp với từ khóa
     */
    List<UniversityDTO> findUniversitiesByName(String name);
    
    /**
     * Tìm kiếm trường đại học theo địa điểm
     * 
     * @param location - Địa điểm cần tìm
     * @return Danh sách các trường đại học ở địa điểm được chỉ định
     */
    List<UniversityDTO> findUniversitiesByLocation(String location);
    
    /**
     * Tìm kiếm trường đại học theo loại (Public/Private)
     * 
     * @param type - Loại trường cần tìm
     * @return Danh sách các trường đại học thuộc loại được chỉ định
     * @throws IllegalArgumentException nếu loại trường không hợp lệ
     */
    List<UniversityDTO> findUniversitiesByType(String type);
    
    /**
     * Lấy danh sách tất cả các địa điểm có trường đại học
     * 
     * @return Danh sách các địa điểm duy nhất
     */
    List<String> getAllLocations();
    
    /**
     * Lấy danh sách tất cả các loại trường đại học
     * 
     * @return Danh sách các loại trường (Public/Private)
     */
    List<String> getAllTypes();
    
    /**
     * Lấy danh sách ảnh của một trường đại học
     * 
     * @param universityId - ID của trường đại học
     * @return Danh sách các URL ảnh của trường đại học
     * @throws EntityNotFoundException nếu không tìm thấy trường đại học
     */
    List<String> getUniversityImages(Integer universityId);
} 