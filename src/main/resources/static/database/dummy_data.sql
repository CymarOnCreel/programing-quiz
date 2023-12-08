INSERT INTO question_categories (category_name) VALUES
('Java'), ('Python');

INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('Basic Syntax', 1),
('Variables', 1),
('Loops', 1),
('Object-Oriented Programming', 1),
('Testing', 1);
INSERT INTO questions (question_text, category_id, subcategory_id) VALUES
 ('What is Java?', 1, 1),
('How to declare a variable in Java?', 1, 2),
('What is a for loop?', 1, 3),
('Explain the concept of inheritance.', 1, 4),
('What is JUnit?', 1, 5);
INSERT INTO answers (answer_text,question_id, is_correct) VALUES
-- Question 1
('A programming language', 1, true),
('A data structure', 1, false),
('A framework for machine learning', 1, false),
('An operating system', 1, false),

-- Question 2
('int x = 10;', 2, true),
('int x == 10;', 2, false),
('int x := 10;', 2, false),
('Declare x = 10;', 2, false),

-- Question 3
('A loop that iterates a specific number of times.', 3, true),
('A way to achieve code reusability through class relationships.', 3, false),
('A design pattern in object-oriented programming.', 3, false),
('A type of data structure.', 3, false),

-- Question 4
('A Java testing framework.', 4, true),
('A JavaScript library for building user interfaces.', 4, false),
('A Python web framework.', 4, false),
('A database management system.', 4, false),

-- Question 5
('A unit testing framework for Java.', 5, true),
('A version control system for Java projects.', 5, false),
('A Java framework for building web applications.', 5, false),
('A programming language for mobile app development.', 5, false);


INSERT INTO users (firstname, lastname, username, password, email, user_category, status) VALUES
('Teszt', 'Elek', 'teszt_elek', '$2a$10$0M..WLk2kdU0JCtd90DSve0uX4FAn81qme/arbftB5yRn6N1dQISO', 'teszt.elek@quiz.com','USER',0),
('Nap', 'Pali', 'nap_pali', '$2a$10$mMpzxBSwalfkMT503sBM0e.4Jd50ls2n1Ybe0Yz6MGASL6v00heN2', 'nap.pali@quiz.com','ADMIN',0),
('John', 'Doe', 'john_doe', '$2a$10$mE8BN5ee8ZrUDBi2MT8HBOafg5IaFD5Yuh5h6zdY8QyrcMmtktXsu', 'john.doe@quiz.com','USER',1);
INSERT INTO answer_scores (user_id, category_id, subcategory_id, quest_count, right_answer_count) VALUES
(1, 1, 1, 5, 3),
(2, 1, 2, 5, 4);