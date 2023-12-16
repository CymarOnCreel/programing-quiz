INSERT INTO question_categories (category_name) VALUES
('Java'), ('Python');

INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('Basic Syntax', 1),
('Variables', 1),
('Loops', 1),
('Object-Oriented Programming', 1),
('Testing', 1);
INSERT INTO questions (question_text, question_status,category_id, subcategory_id) VALUES
 ('What is Java?', 'ACCEPTED',1, 1),
('How to declare a variable in Java?','ACCEPTED', 1, 2),
('What is a for loop?','ACCEPTED', 1, 3),
('Explain the concept of inheritance.','ACCEPTED', 1, 4),
('What is JUnit?','REJECTED', 1, 5);
INSERT INTO answers (answer_text,question_id, is_correct) VALUES
-- Question 1
('A programming language', 1, 1),
('A data structure', 1, 0),
('A framework for machine learning', 1, 0),
('An operating system', 1, 0),

-- Question 2
('int x = 10;', 2, 1),
('int x == 10;', 2, 0),
('int x := 10;', 2, 0),
('Declare x = 10;', 2, 0),

-- Question 3
('A loop that iterates a specific number of times.', 3, 1),
('A way to achieve code reusability through class relationships.', 3, 0),
('A design pattern in object-oriented programming.', 3, 0),
('A type of data structure.', 3, 0),

-- Question 4
('A Java testing framework.', 4, 1),
('A JavaScript library for building user interfaces.', 4, 0),
('A Python web framework.', 4, 0),
('A database management system.', 4, 0),

-- Question 5
('A unit testing framework for Java.', 5, 1),
('A version control system for Java projects.', 5, 0),
('A Java framework for building web applications.', 5, 0),
('A programming language for mobile app development.', 5, 0);


INSERT INTO users (firstname, lastname, username, password, email, user_category, status) VALUES
('Teszt', 'Elek', 'teszt_elek', '$2a$10$0M..WLk2kdU0JCtd90DSve0uX4FAn81qme/arbftB5yRn6N1dQISO', 'teszt.elek@quiz.com','USER',0),
('Nap', 'Pali', 'nap_pali', '$2a$10$mMpzxBSwalfkMT503sBM0e.4Jd50ls2n1Ybe0Yz6MGASL6v00heN2', 'nap.pali@quiz.com','ADMIN',0),
('John', 'Doe', 'john_doe', '$2a$10$mE8BN5ee8ZrUDBi2MT8HBOafg5IaFD5Yuh5h6zdY8QyrcMmtktXsu', 'john.doe@quiz.com','USER',1);
INSERT INTO quiz_scores (user_id, category_id, question_count, right_answer_count,quiz_date) VALUES
(1, 1, 5, 3, '2011-01-01'),
(2, 1, 5, 4, '2012-01-01');