package com.example.reviewkoreauniversitybe.dto;

import com.example.reviewkoreauniversitybe.model.TuitionFee;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuitionFeeDTO {
    private FeeCategory undergraduate;
    private FeeCategory graduate;
    private String currency;
    
    // Nested class for fee categories
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FeeCategory {
        private BigDecimal humanities;
        private BigDecimal science;
        private BigDecimal arts;
        private BigDecimal medicine;
        
        // Constructors
        public FeeCategory() {
        }
        
        public FeeCategory(BigDecimal humanities, BigDecimal science, BigDecimal arts, BigDecimal medicine) {
            this.humanities = humanities;
            this.science = science;
            this.arts = arts;
            this.medicine = medicine;
        }
        
        // Getters
        public BigDecimal getHumanities() {
            return humanities;
        }
        
        public BigDecimal getScience() {
            return science;
        }
        
        public BigDecimal getArts() {
            return arts;
        }
        
        public BigDecimal getMedicine() {
            return medicine;
        }
        
        // Setters
        public void setHumanities(BigDecimal humanities) {
            this.humanities = humanities;
        }
        
        public void setScience(BigDecimal science) {
            this.science = science;
        }
        
        public void setArts(BigDecimal arts) {
            this.arts = arts;
        }
        
        public void setMedicine(BigDecimal medicine) {
            this.medicine = medicine;
        }
    }
    
    // Constructors
    public TuitionFeeDTO() {
    }
    
    public TuitionFeeDTO(FeeCategory undergraduate, FeeCategory graduate, String currency) {
        this.undergraduate = undergraduate;
        this.graduate = graduate;
        this.currency = currency;
    }
    
    // Getters
    public FeeCategory getUndergraduate() {
        return undergraduate;
    }
    
    public FeeCategory getGraduate() {
        return graduate;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    // Setters
    public void setUndergraduate(FeeCategory undergraduate) {
        this.undergraduate = undergraduate;
    }
    
    public void setGraduate(FeeCategory graduate) {
        this.graduate = graduate;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    // Factory method to convert from entity to DTO without using builder
    public static TuitionFeeDTO fromEntity(TuitionFee tuitionFee) {
        if (tuitionFee == null) {
            return null;
        }
        
        TuitionFeeDTO dto = new TuitionFeeDTO();
        
        // Set undergraduate
        FeeCategory undergraduate = new FeeCategory();
        undergraduate.setHumanities(tuitionFee.getUndergraduateHumanities());
        undergraduate.setScience(tuitionFee.getUndergraduateScience());
        undergraduate.setArts(tuitionFee.getUndergraduateArts());
        undergraduate.setMedicine(tuitionFee.getUndergraduateMedicine());
        dto.setUndergraduate(undergraduate);
        
        // Set graduate
        FeeCategory graduate = new FeeCategory();
        graduate.setHumanities(tuitionFee.getGraduateHumanities());
        graduate.setScience(tuitionFee.getGraduateScience());
        graduate.setArts(tuitionFee.getGraduateArts());
        graduate.setMedicine(tuitionFee.getGraduateMedicine());
        dto.setGraduate(graduate);
        
        dto.setCurrency(tuitionFee.getCurrency());
        return dto;
    }
} 