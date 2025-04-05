-- Clear existing data
DELETE FROM tuition_fees;
DELETE FROM university_images;
DELETE FROM university_departments;
DELETE FROM campus_facilities;
DELETE FROM review_pros;
DELETE FROM review_cons;
DELETE FROM reviews;
DELETE FROM universities;

-- Insert sample universities
INSERT INTO universities (id, name, name_korean, location, established, type, website, description, ranking, student_count, faculty_count, has_international_programs, admission_requirements, average_rating, is_active, created_at)
VALUES
(1, 'Seoul National University', '서울대학교', 'Seoul', 1946, 'Public', 'https://www.snu.ac.kr', 'Seoul National University is the top university in South Korea, known for its rigorous academics and research contributions.', 1, 28000, 3200, true, 'TOPIK Level 3 or higher, academic transcripts, statement of purpose', 4.8, true, NOW()),
(2, 'Korea University', '고려대학교', 'Seoul', 1905, 'Private', 'https://www.korea.ac.kr', 'Korea University is one of the SKY universities (the most prestigious in Korea) and is well-known for its business and economics programs.', 3, 25000, 2800, true, 'TOPIK Level 3 or higher, academic transcripts, statement of purpose', 4.6, true, NOW()),
(3, 'Yonsei University', '연세대학교', 'Seoul', 1885, 'Private', 'https://www.yonsei.ac.kr', 'Yonsei University is one of the oldest and most prestigious universities in South Korea, known for its strong medical school.', 2, 26000, 3000, true, 'TOPIK Level 3 or higher, academic transcripts, statement of purpose', 4.7, true, NOW()),
(4, 'KAIST (Korea Advanced Institute of Science and Technology)', '한국과학기술원', 'Daejeon', 1971, 'Public', 'https://www.kaist.ac.kr', 'KAIST is the leading science and technology university in Korea, offering world-class education in engineering and sciences.', 4, 10000, 1200, true, 'TOPIK Level 3 or higher, strong background in STEM subjects', 4.9, true, NOW()),
(5, 'Busan National University', '부산대학교', 'Busan', 1946, 'Public', 'https://www.pusan.ac.kr', 'Busan National University is the top university in the Busan region, with strong programs in engineering and marine sciences.', 5, 22000, 2500, true, 'TOPIK Level 3 or higher, academic transcripts', 4.5, true, NOW());

-- Insert university images
INSERT INTO university_images (id, university_id, image_url, display_order, created_at)
VALUES
(1, 1, 'https://en.snu.ac.kr/webdata/uploads/eng/image/2020/02/index-campas-img01.jpg', 1, NOW()),
(2, 1, 'https://english.seoul.go.kr/wp-content/uploads/2021/03/seoul-national-university-13.jpg', 2, NOW()),
(3, 2, 'https://www.novalandtours.com/images/Gyeonggi/Seoul/Seoul%20National%20University%20Gwanak%20Campus', 1, NOW()),
(4, 2, 'https://mcdonnell.wustl.edu/wp-content/uploads/2020/10/SNU.jpg', 2, NOW()),
(5, 3, 'https://t1.unipage.net/src/uni_75.jpeg', 1, NOW()),
(6, 3, 'https://i.ytimg.com/vi/qlym8AwYVq4/maxresdefault.jpg', 2, NOW()),
(7, 4, 'https://static.wixstatic.com/media/0505b9_04ed69a45009494ea29565dfddf4b9a9~mv2.jpg/v1/fill/w_640%2Ch_569%2Cal_c/0505b9_04ed69a45009494ea29565dfddf4b9a9~mv2.jpg', 1, NOW()),
(8, 5, 'https://ubitto.com/wp-content/uploads/2019/10/imgSub53110_01-1024x576.jpg', 1, NOW());

-- Insert university departments
INSERT INTO university_departments (id, university_id, name, created_at)
VALUES
(1, 1, 'Business Administration', NOW()),
(2, 1, 'Computer Science and Engineering', NOW()),
(3, 1, 'Medicine', NOW()),
(4, 2, 'Economics', NOW()),
(5, 2, 'International Studies', NOW()),
(6, 2, 'Engineering', NOW()),
(7, 3, 'Medicine', NOW()),
(8, 3, 'Business Administration', NOW()),
(9, 4, 'Electrical Engineering', NOW()),
(10, 4, 'Computer Science', NOW()),
(11, 5, 'Marine Sciences', NOW()),
(12, 5, 'Engineering', NOW());

-- Insert campus facilities
INSERT INTO campus_facilities (id, university_id, facility_name, created_at)
VALUES
(1, 1, 'Library', NOW()),
(2, 1, 'Swimming Pool', NOW()),
(3, 1, 'Student Center', NOW()),
(4, 2, 'Library', NOW()),
(5, 2, 'Gymnasium', NOW()),
(6, 2, 'Global Village', NOW()),
(7, 3, 'Library', NOW()),
(8, 3, 'Sports Complex', NOW()),
(9, 4, 'Research Labs', NOW()),
(10, 4, 'Innovation Center', NOW()),
(11, 5, 'Marine Research Center', NOW()),
(12, 5, 'Library', NOW());

-- Insert tuition fees
INSERT INTO tuition_fees (id, university_id, undergraduate_humanities, undergraduate_science, undergraduate_arts, undergraduate_medicine, graduate_humanities, graduate_science, graduate_arts, graduate_medicine, currency, created_at)
VALUES
(1, 1, 4500000, 5300000, 6200000, 8500000, 5200000, 6100000, 7000000, 9500000, 'KRW', NOW()),
(2, 2, 4700000, 5500000, 6500000, 9000000, 5500000, 6300000, 7200000, 10000000, 'KRW', NOW()),
(3, 3, 4600000, 5400000, 6300000, 8700000, 5300000, 6200000, 7100000, 9700000, 'KRW', NOW()),
(4, 4, 4800000, 5600000, 6400000, NULL, 5400000, 6400000, 7300000, NULL, 'KRW', NOW()),
(5, 5, 4300000, 5100000, 6000000, 8200000, 5000000, 5900000, 6800000, 9200000, 'KRW', NOW());

-- Insert reviews
INSERT INTO reviews (id, university_id, author, rating, content, program_studied, year_of_study, is_international, ip_address, is_approved, created_at)
VALUES
(1, 1, 'John Smith', 5, 'Excellent university with great professors and facilities. I had an amazing time studying here.', 'Computer Science', '2020-2023', true, '192.168.1.1', true, NOW()),
(2, 1, 'Minho Kim', 4, 'Good academic environment but very competitive. Professors are knowledgeable but demanding.', 'Business Administration', '2019-2023', false, '192.168.1.2', true, NOW()),
(3, 2, 'Emma Johnson', 5, 'Korea University provides an excellent education with many opportunities for international students.', 'International Studies', '2021-2023', true, '192.168.1.3', true, NOW()),
(4, 3, 'Jihye Park', 4, 'Yonsei has a beautiful campus and good professors. The workload is heavy but worth it.', 'Medicine', '2018-2024', false, '192.168.1.4', true, NOW()),
(5, 4, 'David Wilson', 5, 'KAIST offers cutting-edge research opportunities and excellent facilities for tech enthusiasts.', 'Computer Science', '2020-2022', true, '192.168.1.5', true, NOW());

-- Insert review pros
INSERT INTO review_pros (id, review_id, content, created_at)
VALUES
(1, 1, 'Great facilities', NOW()),
(2, 1, 'Excellent professors', NOW()),
(3, 2, 'Strong alumni network', NOW()),
(4, 2, 'Good job prospects', NOW()),
(5, 3, 'International environment', NOW()),
(6, 3, 'Helpful student services', NOW()),
(7, 4, 'Top medical program', NOW()),
(8, 5, 'Cutting-edge research', NOW()),
(9, 5, 'Industry connections', NOW());

-- Insert review cons
INSERT INTO review_cons (id, review_id, content, created_at)
VALUES
(1, 1, 'Expensive tuition', NOW()),
(2, 1, 'Competitive admission', NOW()),
(3, 2, 'High stress environment', NOW()),
(4, 3, 'Expensive housing nearby', NOW()),
(5, 4, 'Heavy workload', NOW()),
(6, 5, 'Limited humanities courses', NOW());

-- Update average ratings
UPDATE universities u
SET average_rating = (SELECT AVG(r.rating) FROM reviews r WHERE r.university_id = u.id AND r.is_approved = true)
WHERE id IN (SELECT DISTINCT university_id FROM reviews);

select * from universities