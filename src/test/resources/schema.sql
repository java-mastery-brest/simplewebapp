DROP TABLE IF EXISTS gender;
DROP TABLE IF EXISTS employee;

CREATE TABLE gender (
  id INT PRIMARY KEY auto_increment,
  gender VARCHAR(20) NOT NULL
);

CREATE TABLE employee (
  employee_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  department_id INT NOT NULL,
  job_title VARCHAR(250) NOT NULL,
  gender_id INTEGER NOT NULL DEFAULT 1,
  date_of_birth TIMESTAMP NOT NULL,
  CONSTRAINT fk_gender_id FOREIGN KEY (gender_id) REFERENCES gender (id)
);