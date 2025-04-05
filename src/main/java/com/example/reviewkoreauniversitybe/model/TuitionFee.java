package com.example.reviewkoreauniversitybe.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tuition_fees")
public class TuitionFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "undergraduate_humanities")
    private BigDecimal undergraduateHumanities;

    @Column(name = "undergraduate_science")
    private BigDecimal undergraduateScience;

    @Column(name = "undergraduate_arts")
    private BigDecimal undergraduateArts;

    @Column(name = "undergraduate_medicine")
    private BigDecimal undergraduateMedicine;

    @Column(name = "graduate_humanities")
    private BigDecimal graduateHumanities;

    @Column(name = "graduate_science")
    private BigDecimal graduateScience;

    @Column(name = "graduate_arts")
    private BigDecimal graduateArts;

    @Column(name = "graduate_medicine")
    private BigDecimal graduateMedicine;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'KRW'")
    private String currency;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public TuitionFee() {
    }

    public TuitionFee(Integer id, University university, 
                     BigDecimal undergraduateHumanities, BigDecimal undergraduateScience,
                     BigDecimal undergraduateArts, BigDecimal undergraduateMedicine,
                     BigDecimal graduateHumanities, BigDecimal graduateScience,
                     BigDecimal graduateArts, BigDecimal graduateMedicine,
                     String currency, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.university = university;
        this.undergraduateHumanities = undergraduateHumanities;
        this.undergraduateScience = undergraduateScience;
        this.undergraduateArts = undergraduateArts;
        this.undergraduateMedicine = undergraduateMedicine;
        this.graduateHumanities = graduateHumanities;
        this.graduateScience = graduateScience;
        this.graduateArts = graduateArts;
        this.graduateMedicine = graduateMedicine;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public University getUniversity() {
        return university;
    }

    public BigDecimal getUndergraduateHumanities() {
        return undergraduateHumanities;
    }

    public BigDecimal getUndergraduateScience() {
        return undergraduateScience;
    }

    public BigDecimal getUndergraduateArts() {
        return undergraduateArts;
    }

    public BigDecimal getUndergraduateMedicine() {
        return undergraduateMedicine;
    }

    public BigDecimal getGraduateHumanities() {
        return graduateHumanities;
    }

    public BigDecimal getGraduateScience() {
        return graduateScience;
    }

    public BigDecimal getGraduateArts() {
        return graduateArts;
    }

    public BigDecimal getGraduateMedicine() {
        return graduateMedicine;
    }

    public String getCurrency() {
        return currency;
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

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setUndergraduateHumanities(BigDecimal undergraduateHumanities) {
        this.undergraduateHumanities = undergraduateHumanities;
    }

    public void setUndergraduateScience(BigDecimal undergraduateScience) {
        this.undergraduateScience = undergraduateScience;
    }

    public void setUndergraduateArts(BigDecimal undergraduateArts) {
        this.undergraduateArts = undergraduateArts;
    }

    public void setUndergraduateMedicine(BigDecimal undergraduateMedicine) {
        this.undergraduateMedicine = undergraduateMedicine;
    }

    public void setGraduateHumanities(BigDecimal graduateHumanities) {
        this.graduateHumanities = graduateHumanities;
    }

    public void setGraduateScience(BigDecimal graduateScience) {
        this.graduateScience = graduateScience;
    }

    public void setGraduateArts(BigDecimal graduateArts) {
        this.graduateArts = graduateArts;
    }

    public void setGraduateMedicine(BigDecimal graduateMedicine) {
        this.graduateMedicine = graduateMedicine;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 