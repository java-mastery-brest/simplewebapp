INSERT INTO gender (gender) VALUES
  ('male'),
  ('female');

INSERT INTO employee (first_name, last_name, department_id, job_title, gender_id, date_of_birth) VALUES
  ('Aliko', 'Dangote', 1, 'Data scientist', 1, parsedatetime('02-03-1995', 'dd-MM-yyyy')),
  ('Sam', 'Kane', 1, 'Systems analyst', 1, parsedatetime('11-06-1990', 'dd-MM-yyyy')),
  ('Kate', 'Johnson', 2, 'IT coordinator', 2, parsedatetime('26-09-1992', 'dd-MM-yyyy')),
  ('Drake', 'Parker', 3, 'Cloud infrastructure architect', 1, parsedatetime('14-10-1996', 'dd-MM-yyyy')),
  ('Samantha', 'Walker', 4, 'Database analyst', 2, parsedatetime('19-08-1992', 'dd-MM-yyyy'));