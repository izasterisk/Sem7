-- Tạo cơ sở dữ liệu
--CREATE DATABASE PianoMusicCenter;

-- Sử dụng cơ sở dữ liệu vừa tạo
USE PianoMusicCenter;

-- Bảng quản lý người dùng
CREATE TABLE Users (
    userID INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fullName VARCHAR(100),
    email VARCHAR(100),
    role INT 
);

-- Bảng quản lý khóa học
CREATE TABLE Courses (
    courseID INT NOT NULL IDENTITY(1, 1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image VARCHAR(255),
    description TEXT,
    tuitionFee DECIMAL(10, 2) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    category VARCHAR(50),    
    quantity INT DEFAULT 0
);

-- Chèn dữ liệu người dùng
INSERT INTO Users (username, password, fullName, email, role)
VALUES ('admin', '123', 'User One', 'user1@example.com', 1),
       ('user', '123', 'User Two', 'user2@example.com', 2);

-- Chèn dữ liệu khóa học
INSERT INTO Courses (name, image, description, tuitionFee, startDate, endDate, category, quantity)
VALUES 
('Course 1', 'images/course1.jpg', 'Description for Course 1', 100.00, '2024-07-01', '2024-07-15', 'Category 1', 10),
('Course 2', 'images/course2.jpg', 'Description for Course 2', 200.00, '2024-07-02', '2024-07-16', 'Category 2', 15),
('Course 3', 'images/course3.jpg', 'Description for Course 3', 300.00, '2024-07-03', '2024-07-17', 'Category 3', 20),
('Course 4', 'images/course4.jpg', 'Description for Course 4', 400.00, '2024-07-04', '2024-07-18', 'Category 4', 25),
('Course 5', 'images/course5.jpg', 'Description for Course 5', 500.00, '2024-07-05', '2024-07-19', 'Category 5', 30),
('Course 6', 'images/course6.jpg', 'Description for Course 6', 600.00, '2024-07-06', '2024-07-20', 'Category 6', 35),
('Course 7', 'images/course7.jpg', 'Description for Course 7', 700.00, '2024-07-07', '2024-07-21', 'Category 7', 40),
('Course 8', 'images/course8.jpg', 'Description for Course 8', 800.00, '2024-07-08', '2024-07-22', 'Category 8', 45),
('Course 9', 'images/course9.jpg', 'Description for Course 9', 900.00, '2024-07-09', '2024-07-23', 'Category 9', 50),
('Course 10', 'images/course10.jpg', 'Description for Course 10', 1000.00, '2024-07-10', '2024-07-24', 'Category 10', 55),
('Course 11', 'images/course11.jpg', 'Description for Course 11', 1100.00, '2024-07-11', '2024-07-25', 'Category 11', 60),
('Course 12', 'images/course12.jpg', 'Description for Course 12', 1200.00, '2024-07-12', '2024-07-26', 'Category 12', 65),
('Course 13', 'images/course13.jpg', 'Description for Course 13', 1300.00, '2024-07-13', '2024-07-27', 'Category 13', 70),
('Course 14', 'images/course14.jpg', 'Description for Course 14', 1400.00, '2024-07-14', '2024-07-28', 'Category 14', 75),
('Course 15', 'images/course15.jpg', 'Description for Course 15', 1500.00, '2024-07-15', '2024-07-29', 'Category 15', 80),
('Course 16', 'images/course16.jpg', 'Description for Course 16', 1600.00, '2024-07-16', '2024-07-30', 'Category 16', 85),
('Course 17', 'images/course17.jpg', 'Description for Course 17', 1700.00, '2024-07-17', '2024-07-31', 'Category 17', 90),
('Course 18', 'images/course18.jpg', 'Description for Course 18', 1800.00, '2024-07-18', '2024-08-01', 'Category 18', 95),
('Course 19', 'images/course19.jpg', 'Description for Course 19', 1900.00, '2024-07-19', '2024-08-02', 'Category 19', 100),
('Course 20', 'images/course20.jpg', 'Description for Course 20', 2000.00, '2024-07-20', '2024-08-03', 'Category 20', 105);
