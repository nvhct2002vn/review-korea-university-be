package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Integer> {
    
    // Find tuition fee by university id
    Optional<TuitionFee> findByUniversityId(Integer universityId);
} 