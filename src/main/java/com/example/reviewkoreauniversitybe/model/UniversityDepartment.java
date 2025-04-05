package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "university_departments")
public class UniversityDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public UniversityDepartment() {
    }
    
    public UniversityDepartment(Integer id, University university, String name, LocalDateTime createdAt) {
        this.id = id;
        this.university = university;
        this.name = name;
        this.createdAt = createdAt;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public University getUniversity() {
        return university;
    }
    
    public String getName() {
        return name;
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
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 