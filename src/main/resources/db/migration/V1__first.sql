CREATE DATABASE IF NOT EXISTS employee;

CREATE TABLE IF NOT EXISTS emp(
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(45),
last_name VARCHAR(45),
salary INT);

INSERT INTO emp (id, first_name, last_name, salary) VALUES (1, 'isha', 'singh', 2000000);