package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDao implements DAO<Employee> {

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Employee> employeeRowMapper = ((rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getLong("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        return employee;
    });

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<Employee> getEmployee(Long id) {
        String sqlQuery = "SELECT employee_id, first_name, last_name, department_id, job_title FROM employee " +
                "WHERE employee_id = ?";
        Employee employee = null;
        try{
            employee = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, employeeRowMapper);
        }
        catch (DataAccessException ex){
            ex.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        String sqlQuery = "SELECT employee_id, first_name, last_name, department_id, job_title FROM employee";
        return jdbcTemplate.query(sqlQuery, employeeRowMapper);
    }

    @Override
    public void deleteEmployee(Long id) {
        String sqlQuery = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void updateEmployee(Employee employee, Long id) {
        String sqlQuery = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ?" +
                "WHERE employee_id = ?";
        int update = jdbcTemplate.update(sqlQuery, employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), id);
        if(update < 1){
            throw new IllegalStateException("Couldn't update employee data");
        }
    }

    @Override
    public void insertEmployee(Employee employee) {
        String sqlQuery = "INSERT INTO employee (first_name, last_name, department_id, job_title) " +
                " values (?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sqlQuery, employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle());
        if (insert < 1) {
            throw new IllegalStateException("Employee couldn't be added");
        }
    }
}
