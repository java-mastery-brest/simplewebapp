DROP TABLE IF EXISTS employee;

CREATE TABLE employee
(
  employee_id int NOT NULL PRIMARY KEY auto_increment,
  first_name varchar(255),
  last_name varchar(255),
  department_id int,
  job_title varchar(255),
  gender varchar(255),
  date_of_birth DATE
);