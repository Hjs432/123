CREATE DATABASE IF NOT EXISTS city_life DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE city_life;

CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    parent_id INT DEFAULT 0,
    sort_order INT DEFAULT 0,
    status INT DEFAULT 1
);

INSERT INTO category (name, parent_id, sort_order) VALUES
('招聘信息', 0, 1),
('求职信息', 0, 2),
('培训信息', 0, 3),
('房屋信息', 0, 4),
('求购信息', 0, 5),
('出售信息', 0, 6),
('招商信息', 0, 7),
('公寓信息', 0, 8),
('车辆信息', 0, 9),
('寻求启示', 0, 10),
('家教信息', 0, 11),
('其他', 0, 99);

CREATE TABLE IF NOT EXISTS info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    contact_phone VARCHAR(50),
    contact_name VARCHAR(50),
    contact_email VARCHAR(100),
    publish_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status INT DEFAULT 0,
    is_paid INT DEFAULT 0,
    views INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status INT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'admin'
);

INSERT INTO admin (username, password) VALUES ('admin', '123456');