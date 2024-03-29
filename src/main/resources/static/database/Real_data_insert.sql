-- Inserting data into Question Category Table
INSERT INTO question_categories (category_name) VALUES
('Java'),
('C#'),
('C++'),
('Python'),
('PHP');

-- Inserting data into Question Subcategory Table for Java
INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('OOP', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('Alapok', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('Emelt', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('Adatbázis', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('Grafika', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('Maven', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('JavaEE', (SELECT id FROM question_categories WHERE category_name = 'Java')),
('ToChangeSubcategory', (SELECT id FROM question_categories WHERE category_name = 'Java'));

INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('C# Subcategory 1', (SELECT id FROM question_categories WHERE category_name = 'C#')),
('C# Subcategory 2', (SELECT id FROM question_categories WHERE category_name = 'C#')),
('ToChangeSubcategory', (SELECT id FROM question_categories WHERE category_name = 'C#'));


INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('C++ Subcategory 1', (SELECT id FROM question_categories WHERE category_name = 'C++')),
('C++ Subcategory 2', (SELECT id FROM question_categories WHERE category_name = 'C++')),
('ToChangeSubcategory', (SELECT id FROM question_categories WHERE category_name = 'C++'));

INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('Python Subcategory 1', (SELECT id FROM question_categories WHERE category_name = 'Python')),
('Python Subcategory 2', (SELECT id FROM question_categories WHERE category_name = 'Python')),
('ToChangeSubcategory', (SELECT id FROM question_categories WHERE category_name = 'Python'));


INSERT INTO question_subcategories (subcategory_name, category_id) VALUES
('PHP Subcategory 1', (SELECT id FROM question_categories WHERE category_name = 'PHP')),
('PHP Subcategory 2', (SELECT id FROM question_categories WHERE category_name = 'PHP')),
('ToChangeSubcategory', (SELECT id FROM question_categories WHERE category_name = 'PHP'));
