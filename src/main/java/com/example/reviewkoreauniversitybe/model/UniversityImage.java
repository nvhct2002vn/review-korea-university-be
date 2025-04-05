package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "university_images")
public class UniversityImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public UniversityImage() {
    }
    
    public UniversityImage(Integer id, University university, String imageUrl, 
                          Integer displayOrder, LocalDateTime createdAt) {
        this.id = id;
        this.university = university;
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
        this.createdAt = createdAt;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public University getUniversity() {
        return university;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public Integer getDisplayOrder() {
        return displayOrder;
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
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 