package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "campus_facilities")
public class CampusFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "facility_name", nullable = false)
    private String facilityName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public CampusFacility() {
    }
    
    public CampusFacility(Integer id, University university, String facilityName, LocalDateTime createdAt) {
        this.id = id;
        this.university = university;
        this.facilityName = facilityName;
        this.createdAt = createdAt;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public University getUniversity() {
        return university;
    }
    
    public String getFacilityName() {
        return facilityName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUniversity(University university) {
        this.university = university;
    }
    
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 