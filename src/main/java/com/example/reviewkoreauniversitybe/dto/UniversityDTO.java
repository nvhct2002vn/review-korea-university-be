package com.example.reviewkoreauniversitybe.dto;

import com.example.reviewkoreauniversitybe.model.University;

import java.math.BigDecimal;
import java.util.List;

public class UniversityDTO {
    private Integer id;
    private String name;
    private String nameKorean;
    private String location;
    private Integer established;
    private String type;
    private String website;
    private String description;
    private Integer ranking;
    private Integer studentCount;
    private Integer facultyCount;
    private Boolean hasInternationalPrograms;
    private String admissionRequirements;
    private BigDecimal averageRating;
    private Long reviewCount;
    private List<String> images;
    private List<String> departments;
    private List<String> facilities;
    private TuitionFeeDTO tuition;
    
    // Constructors
    public UniversityDTO() {
    }
    
    public UniversityDTO(Integer id, String name, String nameKorean, String location, Integer established,
                        String type, String website, String description, Integer ranking,
                        Integer studentCount, Integer facultyCount, Boolean hasInternationalPrograms,
                        String admissionRequirements, BigDecimal averageRating, Long reviewCount,
                        List<String> images, List<String> departments, List<String> facilities,
                        TuitionFeeDTO tuition) {
        this.id = id;
        this.name = name;
        this.nameKorean = nameKorean;
        this.location = location;
        this.established = established;
        this.type = type;
        this.website = website;
        this.description = description;
        this.ranking = ranking;
        this.studentCount = studentCount;
        this.facultyCount = facultyCount;
        this.hasInternationalPrograms = hasInternationalPrograms;
        this.admissionRequirements = admissionRequirements;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.images = images;
        this.departments = departments;
        this.facilities = facilities;
        this.tuition = tuition;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getNameKorean() {
        return nameKorean;
    }
    
    public String getLocation() {
        return location;
    }
    
    public Integer getEstablished() {
        return established;
    }
    
    public String getType() {
        return type;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Integer getRanking() {
        return ranking;
    }
    
    public Integer getStudentCount() {
        return studentCount;
    }
    
    public Integer getFacultyCount() {
        return facultyCount;
    }
    
    public Boolean getHasInternationalPrograms() {
        return hasInternationalPrograms;
    }
    
    public String getAdmissionRequirements() {
        return admissionRequirements;
    }
    
    public BigDecimal getAverageRating() {
        return averageRating;
    }
    
    public Long getReviewCount() {
        return reviewCount;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public List<String> getDepartments() {
        return departments;
    }
    
    public List<String> getFacilities() {
        return facilities;
    }
    
    public TuitionFeeDTO getTuition() {
        return tuition;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setNameKorean(String nameKorean) {
        this.nameKorean = nameKorean;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setEstablished(Integer established) {
        this.established = established;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
    
    public void setFacultyCount(Integer facultyCount) {
        this.facultyCount = facultyCount;
    }
    
    public void setHasInternationalPrograms(Boolean hasInternationalPrograms) {
        this.hasInternationalPrograms = hasInternationalPrograms;
    }
    
    public void setAdmissionRequirements(String admissionRequirements) {
        this.admissionRequirements = admissionRequirements;
    }
    
    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
    
    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
    
    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }
    
    public void setTuition(TuitionFeeDTO tuition) {
        this.tuition = tuition;
    }
    
    // Factory method to convert from entity to DTO without using builder
    public static UniversityDTO fromEntity(University university) {
        UniversityDTO dto = new UniversityDTO();
        dto.setId(university.getId());
        dto.setName(university.getName());
        dto.setNameKorean(university.getNameKorean());
        dto.setLocation(university.getLocation());
        dto.setEstablished(university.getEstablished());
        if (university.getType() != null) {
            dto.setType(university.getType().toString());
        }
        dto.setWebsite(university.getWebsite());
        dto.setDescription(university.getDescription());
        dto.setRanking(university.getRanking());
        dto.setStudentCount(university.getStudentCount());
        dto.setFacultyCount(university.getFacultyCount());
        dto.setHasInternationalPrograms(university.getHasInternationalPrograms());
        dto.setAdmissionRequirements(university.getAdmissionRequirements());
        dto.setAverageRating(university.getAverageRating());
        return dto;
    }
} 