package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "name_korean")
    private String nameKorean;

    @Column(nullable = false)
    private String location;

    private Integer established;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UniversityType type;

    private String website;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer ranking;

    @Column(name = "student_count")
    private Integer studentCount;

    @Column(name = "faculty_count")
    private Integer facultyCount;

    @Column(name = "has_international_programs")
    private Boolean hasInternationalPrograms;

    @Column(name = "admission_requirements", columnDefinition = "TEXT")
    private String admissionRequirements;

    @Column(name = "average_rating", precision = 3, scale = 1)
    private BigDecimal averageRating;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum UniversityType {
        Public, Private
    }

    // Constructors
    public University() {
    }

    public University(Integer id, String name, String nameKorean, String location, Integer established,
                     UniversityType type, String website, String description, Integer ranking,
                     Integer studentCount, Integer facultyCount, Boolean hasInternationalPrograms,
                     String admissionRequirements, BigDecimal averageRating, Boolean isActive,
                     LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public UniversityType getType() {
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

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public void setType(UniversityType type) {
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 