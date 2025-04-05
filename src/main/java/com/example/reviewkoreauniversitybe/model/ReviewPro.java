package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_pros")
public class ReviewPro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors
    public ReviewPro() {
    }

    public ReviewPro(Integer id, Review review, String content, LocalDateTime createdAt) {
        this.id = id;
        this.review = review;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Review getReview() {
        return review;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 