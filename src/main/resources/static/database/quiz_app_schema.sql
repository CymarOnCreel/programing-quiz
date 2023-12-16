CREATE SCHEMA `quiz` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci ;

Use quiz;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    user_category ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    status TINYINT NOT NULL DEFAULT 0
);

-- Question Category Table
CREATE TABLE question_categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(70) NOT NULL
);

-- Question Subcategory Table
CREATE TABLE question_subcategories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    subcategory_name VARCHAR(70) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES question_categories(id)
);

-- Questions Table
CREATE TABLE questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_text TEXT NOT NULL, 
    question_status ENUM('ACCEPTED', 'REJECTED', 'IN_PROGRESS') NOT NULL DEFAULT 'IN_PROGRESS',
    category_id INT,
    subcategory_id INT,
    FOREIGN KEY (category_id) REFERENCES question_categories(id),
    FOREIGN KEY (subcategory_id) REFERENCES question_subcategories(id)
);

-- Answers Table
CREATE TABLE answers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    answer_text TEXT NOT NULL,
    question_id INT,
    is_correct TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (question_id) REFERENCES questions(id)
);


-- Quiz Score Table
CREATE TABLE quiz_scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    category_id INT,
    question_count INT,
    right_answer_count INT,
    quiz_date DATE NOT NULL DEFAULT "1999-01-01",
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES question_categories(id)
);