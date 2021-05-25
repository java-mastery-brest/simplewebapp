package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.mapper.row_mapper.EmployeeRowMapper;
import com.mastery.java.task.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert =  new SimpleJdbcInsert(jdbcTemplate)
                                .withSchemaName("employeedb.public")
                                .withTableName("\"‘employee’ \"")
                                .usingColumns("first_name", "last_name", "department_id",
                                        "job_title", "gender_id", "date_of_birth")
                                .usingGeneratedKeyColumns("employee_id");
        ;
    }

    @Override
    public Employee create(Employee employee) {
        Number employeeId = jdbcInsert.executeAndReturnKey(
                Map.of("first_name", employee.getFirstName(),
                       "last_name", employee.getLastName(),
                       "department_id", employee.getDepartmentId(),
                       "job_title", employee.getJobTitle(),
                       "gender_id", employee.getGender().getId(),
                       "date_of_birth", employee.getDateOfBirth())
        );

        return employee.setId(employeeId.longValue());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select employee_id, first_name, last_name, department_id, job_title, gender_id, date_of_birth " +
                        "from public.\"‘employee’ \"",
                new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(long id) {
        return jdbcTemplate.query
                ("select employee_id, first_name, last_name, department_id, job_title, gender_id, date_of_birth " +
                                "from public.\"‘employee’ \" where employee_id = ?",
                new Object[]{id}, new EmployeeRowMapper())
                .stream().findAny();
    }

    @Override
    public Employee update(long id, Employee employee) {
        jdbcTemplate.update(
                "update public.\"‘employee’ \" set first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender_id = ?, date_of_birth = ? " +
                        "where employee_id = ?",
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender().getId(), employee.getDateOfBirth(), id);

        return employee.setId(id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from public.\"‘employee’ \" where employee_id = ?", id);
    }
}
