package com.example.reviewkoreauniversitybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.example.reviewkoreauniversitybe",
    "com.example.reviewkoreauniversitybe.controller",
    "com.example.reviewkoreauniversitybe.service",
    "com.example.reviewkoreauniversitybe.service.impl",
    "com.example.reviewkoreauniversitybe.repository",
    "com.example.reviewkoreauniversitybe.config"
})
public class ReviewKoreaUniversityBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewKoreaUniversityBeApplication.class, args);
    }

}
