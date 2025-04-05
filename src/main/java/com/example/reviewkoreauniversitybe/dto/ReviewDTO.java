package com.example.reviewkoreauniversitybe.dto;

import com.example.reviewkoreauniversitybe.model.Review;
import com.example.reviewkoreauniversitybe.model.ReviewCon;
import com.example.reviewkoreauniversitybe.model.ReviewPro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewDTO {
    private Integer id;
    private Integer universityId;
    private String author;
    private Integer rating;
    private String content;
    private String programStudied;
    private String yearOfStudy;
    private Boolean isInternational;
    private LocalDateTime createdAt;
    private List<String> pros;
    private List<String> cons;
    
    // Constructors
    public ReviewDTO() {
    }
    
    public ReviewDTO(Integer id, Integer universityId, String author, Integer rating, String content, String programStudied,
                   String yearOfStudy, Boolean isInternational, LocalDateTime createdAt,
                   List<String> pros, List<String> cons) {
        this.id = id;
        this.universityId = universityId;
        this.author = author;
        this.rating = rating;
        this.content = content;
        this.programStudied = programStudied;
        this.yearOfStudy = yearOfStudy;
        this.isInternational = isInternational;
        this.createdAt = createdAt;
        this.pros = pros;
        this.cons = cons;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public Integer getUniversityId() {
        return universityId;
    }
    
    public String getAuthor() {
        return author;
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public List<String> getPros() {
        return pros;
    }
    
    public List<String> getCons() {
        return cons;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }
    
    public void setAuthor(String author) {
        this.author = author;
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
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setPros(List<String> pros) {
        this.pros = pros;
    }
    
    public void setCons(List<String> cons) {
        this.cons = cons;
    }
    
    // Factory method to convert from entity to DTO without using builder
    public static ReviewDTO fromEntity(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        if (review.getUniversity() != null) {
            dto.setUniversityId(review.getUniversity().getId());
        }
        dto.setAuthor(review.getAuthor());
        dto.setRating(review.getRating());
        dto.setContent(review.getContent());
        dto.setProgramStudied(review.getProgramStudied());
        dto.setYearOfStudy(review.getYearOfStudy());
        dto.setIsInternational(review.getIsInternational());
        dto.setCreatedAt(review.getCreatedAt());
        
        if (review.getPros() != null) {
            dto.setPros(review.getPros().stream()
                    .map(ReviewPro::getContent)
                    .collect(Collectors.toList()));
        }
        
        if (review.getCons() != null) {
            dto.setCons(review.getCons().stream()
                    .map(ReviewCon::getContent)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}