package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false)
    private String author;

    private String email;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "program_studied")
    private String programStudied;

    @Column(name = "year_of_study")
    private String yearOfStudy;

    @Column(name = "is_international")
    private Boolean isInternational;

    @Column(name = "is_approved")
    private Boolean isApproved = true;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPro> pros = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewCon> cons = new ArrayList<>();

    // Constructors
    public Review() {
    }

    public Review(Integer id, University university, String author, String email, Integer rating, String content,
                 String programStudied, String yearOfStudy, Boolean isInternational, Boolean isApproved,
                 String ipAddress, LocalDateTime createdAt, LocalDateTime updatedAt,
                 List<ReviewPro> pros, List<ReviewCon> cons) {
        this.id = id;
        this.university = university;
        this.author = author;
        this.email = email;
        this.rating = rating;
        this.content = content;
        this.programStudied = programStudied;
        this.yearOfStudy = yearOfStudy;
        this.isInternational = isInternational;
        this.isApproved = isApproved;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pros = pros;
        this.cons = cons;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public University getUniversity() {
        return university;
    }

    public String getAuthor() {
        return author;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getProgramStudied() {
        return programStudied;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public Boolean getIsInternational() {
        return isInternational;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ReviewPro> getPros() {
        return pros;
    }

    public List<ReviewCon> getCons() {
        return cons;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProgramStudied(String programStudied) {
        this.programStudied = programStudied;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void setIsInternational(Boolean isInternational) {
        this.isInternational = isInternational;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPros(List<ReviewPro> pros) {
        this.pros = pros;
    }

    public void setCons(List<ReviewCon> cons) {
        this.cons = cons;
    }
} 