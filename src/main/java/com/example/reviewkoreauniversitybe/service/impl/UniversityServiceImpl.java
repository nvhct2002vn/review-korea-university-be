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

/**
 * Lớp triển khai các phương thức dịch vụ liên quan đến trường đại học
 * Chứa logic xử lý và kết nối với các repository để truy xuất dữ liệu
 */
@Service
@Transactional(readOnly = true) // Đặt tất cả các phương thức ở chế độ chỉ đọc mặc định
public class UniversityServiceImpl implements UniversityService {
    
    // Tiêm các repository cần thiết
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
    
    /**
     * {@inheritDoc}
     * Lấy thông tin chi tiết của trường đại học theo ID
     * và bổ sung thêm các thông tin liên quan (ảnh, khoa, cơ sở vật chất, học phí)
     */
    @Override
    public UniversityDTO getUniversityById(Integer id) {
        // Tìm trường đại học theo ID, nếu không tìm thấy sẽ ném ra ngoại lệ
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + id));
        
        // Chuyển đổi entity thành DTO và bổ sung thông tin
        return enrichUniversityDTO(UniversityDTO.fromEntity(university));
    }
    
    /**
     * {@inheritDoc}
     * Lấy danh sách các trường đại học đang hoạt động với phân trang
     */
    @Override
    public Page<UniversityDTO> getAllUniversities(Pageable pageable) {
        // Tìm các trường đại học đang hoạt động (isActive = true)
        return universityRepository.findByIsActiveTrue(pageable)
                .map(UniversityDTO::fromEntity) // Chuyển đổi từng entity thành DTO
                .map(this::enrichUniversityDTO); // Bổ sung thông tin cho mỗi DTO
    }
    
    /**
     * {@inheritDoc}
     * Lấy danh sách các trường đại học được xếp hạng cao nhất
     */
    @Override
    public Page<UniversityDTO> getTopRatedUniversities(Pageable pageable) {
        // Tìm các trường đại học được sắp xếp theo xếp hạng
        return universityRepository.findTopRatedUniversities(pageable)
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO);
    }
    
    /**
     * {@inheritDoc}
     * Tìm kiếm trường đại học theo tên (tiếng Anh hoặc tiếng Hàn)
     */
    @Override
    public List<UniversityDTO> findUniversitiesByName(String name) {
        // Tìm kiếm không phân biệt chữ hoa chữ thường trong cả tên tiếng Anh và tiếng Hàn
        return universityRepository.findByNameContainingIgnoreCaseOrNameKoreanContainingIgnoreCase(name, name)
                .stream()
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     * Tìm kiếm trường đại học theo địa điểm
     */
    @Override
    public List<UniversityDTO> findUniversitiesByLocation(String location) {
        // Tìm kiếm không phân biệt chữ hoa chữ thường trong địa điểm
        return universityRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(UniversityDTO::fromEntity)
                .map(this::enrichUniversityDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     * Tìm kiếm trường đại học theo loại (Public/Private)
     */
    @Override
    public List<UniversityDTO> findUniversitiesByType(String type) {
        try {
            // Chuyển đổi chuỗi thành enum UniversityType
            University.UniversityType universityType = University.UniversityType.valueOf(type);
            return universityRepository.findByType(universityType)
                    .stream()
                    .map(UniversityDTO::fromEntity)
                    .map(this::enrichUniversityDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            // Nếu loại trường không hợp lệ, ném ra ngoại lệ
            throw new IllegalArgumentException("Invalid university type: " + type);
        }
    }
    
    /**
     * {@inheritDoc}
     * Lấy danh sách tất cả các địa điểm có trường đại học
     */
    @Override
    public List<String> getAllLocations() {
        return universityRepository.findAllLocations();
    }
    
    /**
     * {@inheritDoc}
     * Lấy danh sách tất cả các loại trường đại học
     */
    @Override
    public List<String> getAllTypes() {
        // Chuyển đổi các giá trị enum thành chuỗi
        return Arrays.stream(University.UniversityType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     * Lấy danh sách ảnh của một trường đại học
     */
    @Override
    public List<String> getUniversityImages(Integer universityId) {
        // Kiểm tra xem trường đại học có tồn tại không
        if (!universityRepository.existsById(universityId)) {
            throw new EntityNotFoundException("University not found with id: " + universityId);
        }
        
        // Lấy danh sách ảnh
        List<String> imageUrls = new ArrayList<>();
        universityImageRepository.findByUniversityIdOrderByDisplayOrderAsc(universityId)
                .forEach(image -> imageUrls.add(image.getImageUrl()));
        
        return imageUrls;
    }
    
    /**
     * Bổ sung thông tin chi tiết cho UniversityDTO từ các repository liên quan
     * 
     * @param universityDTO - DTO cần bổ sung thông tin
     * @return DTO đã được bổ sung đầy đủ thông tin
     */
    private UniversityDTO enrichUniversityDTO(UniversityDTO universityDTO) {
        // Lấy danh sách ảnh
        List<String> imageUrls = new ArrayList<>();
        universityImageRepository.findByUniversityIdOrderByDisplayOrderAsc(universityDTO.getId())
                .forEach(image -> imageUrls.add(image.getImageUrl()));
        universityDTO.setImages(imageUrls);
        
        // Lấy danh sách khoa
        List<String> departments = new ArrayList<>();
        universityDepartmentRepository.findByUniversityId(universityDTO.getId())
                .forEach(dept -> departments.add(dept.getName()));
        universityDTO.setDepartments(departments);
        
        // Lấy danh sách cơ sở vật chất
        List<String> facilities = new ArrayList<>();
        campusFacilityRepository.findByUniversityId(universityDTO.getId())
                .forEach(facility -> facilities.add(facility.getFacilityName()));
        universityDTO.setFacilities(facilities);
        
        // Lấy thông tin học phí
        universityDTO.setTuition(
                tuitionFeeRepository.findByUniversityId(universityDTO.getId())
                        .map(TuitionFeeDTO::fromEntity)
                        .orElse(null)
        );
        
        // Lấy số lượng đánh giá
        universityDTO.setReviewCount(
                reviewRepository.countByUniversityIdAndIsApprovedTrue(universityDTO.getId())
        );
        
        return universityDTO;
    }
} 