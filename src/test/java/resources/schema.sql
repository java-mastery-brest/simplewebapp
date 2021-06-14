CREATE TABLE public.employee
(
  employee_id integer NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
  first_name text,
  last_name text,
  department_id integer,
  job_title text,
  gender text,
  date_of_birth date,
  CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);