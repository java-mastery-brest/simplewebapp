package com.mastery.java.task.mapper.row_mapper;

import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        return new Employee(
                rs.getLong("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("department_id"),
                rs.getString("job_title"),
                Gender.genderById(rs.getInt("gender_id")),
                rs.getTimestamp("date_of_birth").toLocalDateTime()
        );
    }
}
