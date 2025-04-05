package com.example.reviewkoreauniversitybe.repository;

import com.example.reviewkoreauniversitybe.model.UniversityDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityDepartmentRepository extends JpaRepository<UniversityDepartment, Integer> {
    
    // Find departments by university id
    List<UniversityDepartment> findByUniversityId(Integer universityId);
} 