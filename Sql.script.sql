-- skapar databasen -- 
CREATE DATABASE library

-- välj databas -- 
USE library; 

-- tabellerna -- 
CREATE TABLE books ( id INT PRIMARY KEY AUTO_INCREMENT,
				     title VARCHAR(255) NOT NULL,
                     author VARCHAR(255) NOT NULL,
                     available BOOLEAN NOT NULL DEFAULT TRUE );
				
CREATE TABLE loans ( id INT PRIMARY KEY AUTO_INCREMENT,
                     user_name VARCHAR(255) NOT NULL,
                     book_id INT NOT NULL,
                     loan_date DATE NOT NULL,
                     return_date DATE,
                     FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE );
                     
-- hämta data från tabellerna --
SELECT * FROM loans;
SELECT * FROM books;
