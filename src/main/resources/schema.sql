-- Create tables if not exists

-- Universities
CREATE TABLE IF NOT EXISTS universities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    name_korean VARCHAR(255),
    location VARCHAR(100) NOT NULL,
    established INT,
    type VARCHAR(50) NOT NULL,
    website VARCHAR(255),
    description TEXT,
    ranking INT,
    student_count INT,
    faculty_count INT,
    has_international_programs BOOLEAN,
    admission_requirements TEXT,
    average_rating DECIMAL(3,1),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- University Images
CREATE TABLE IF NOT EXISTS university_images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    university_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    display_order INT,
    created_at TIMESTAMP,
    FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE
);

-- University Departments
CREATE TABLE IF NOT EXISTS university_departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    university_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE
);

-- Campus Facilities
CREATE TABLE IF NOT EXISTS campus_facilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    university_id INT NOT NULL,
    facility_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE
);

-- Tuition Fees
CREATE TABLE IF NOT EXISTS tuition_fees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    university_id INT NOT NULL,
    undergraduate_humanities DECIMAL(15,2),
    undergraduate_science DECIMAL(15,2),
    undergraduate_arts DECIMAL(15,2),
    undergraduate_medicine DECIMAL(15,2),
    graduate_humanities DECIMAL(15,2),
    graduate_science DECIMAL(15,2),
    graduate_arts DECIMAL(15,2),
    graduate_medicine DECIMAL(15,2),
    currency VARCHAR(10) NOT NULL DEFAULT 'KRW',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE
);

-- Reviews
CREATE TABLE IF NOT EXISTS reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    university_id INT NOT NULL,
    author VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    content TEXT,
    program_studied VARCHAR(255),
    year_of_study VARCHAR(50),
    is_international BOOLEAN,
    ip_address VARCHAR(50),
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP,
    FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE
);

-- Review Pros
CREATE TABLE IF NOT EXISTS review_pros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE
);

-- Review Cons
CREATE TABLE IF NOT EXISTS review_cons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE
); 