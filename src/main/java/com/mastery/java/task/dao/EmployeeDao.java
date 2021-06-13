package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;


@Component
public class EmployeeDao {


    @Autowired
    JdbcTemplate jdbc;


    public Employee findById(Integer id){
        String query="SELECT * FROM employee WHERE employee_id = ?";
        final Employee[] employee = new Employee[1];
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    employee[0] = getResultEmployee(resultSet);
                }
                return  null;
            }
        });
        return employee[0];
    }


    public List<Employee> findAll(){
        String query="SELECT * FROM employee ";
        List<Employee> list = new ArrayList<>();
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ResultSet resultSet = ps.executeQuery();
                Employee employee = null;
                while (resultSet.next()) {
                    list.add(getResultEmployee(resultSet));
                }
                return  null;
            }
        });
        return list;
    }


    public List<Employee> findByDepartmentId(Integer departmentId){
        String query="SELECT * FROM employee WHERE department_id = ?";
        List<Employee> list = new ArrayList<>();
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setInt(1, departmentId);
                ResultSet resultSet = ps.executeQuery();
                Employee employee = null;
                while (resultSet.next()) {
                   list.add(getResultEmployee(resultSet));
                }
                return  null;
            }
        });
        return list;
    }


    public void deleteEmployee (Integer id){
        String query= "DELETE FROM employee WHERE employee_id = ?";
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
                return ps.execute();
            }
        });
        System.out.println("Data deleted successfully");
    }


    public void newEmployee(Employee newEmployee){
        String query="INSERT INTO employee ( first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES ( ?, ?, ?, ?, ?, ?)";
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                setParametersForEmployee(newEmployee, ps);

                return ps.execute();
            }
        });
        System.out.println("Data inserted Successfully");
    }


    public void updateEmployee(Employee employee, Integer id){
        String query="UPDATE employee SET  first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";
        jdbc.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                setParametersForEmployee(employee, ps);
                ps.setInt(7,employee.getEmployeeId());
                return ps.execute();
            }
        });
        System.out.println("Data updated Successfully");
    }


//-----------------------local methods-----------------------------------


    private Employee getResultEmployee(ResultSet resultSet) throws SQLException {
        Employee employee;
        Integer employee_id = resultSet.getInt(1);
        String first_name = resultSet.getString(2);
        String last_name = resultSet.getString(3);
        Integer department_id = resultSet.getInt(4);
        String job_title = resultSet.getString(5);
        String gender = resultSet.getString(6);
        Gender genderEnum = null;
        if (gender.equals("Male")) {
            genderEnum = MALE;
        } else if (gender.equals("Female")) {
            genderEnum = FEMALE;
        }
        LocalDate date_of_birth = resultSet.getDate(7).toLocalDate();

        employee = new Employee(employee_id, first_name, last_name, department_id, job_title, genderEnum, date_of_birth);
        return employee;
    }


    private void setParametersForEmployee(Employee employee, PreparedStatement selectStatement) throws SQLException {
        Gender genderEnum = employee.getGender();
        String stringGender = "";
        if (genderEnum == MALE){
            stringGender = "Male";
        } else if( genderEnum == FEMALE){
            stringGender = "Female";
        }
        selectStatement.setString(1, employee.getFirstName());
        selectStatement.setString(2, employee.getLastName());
        selectStatement.setInt(3, employee.getDepartmentId());
        selectStatement.setString(4, employee.getJobTitle());
        selectStatement.setString(5, stringGender);
        selectStatement.setDate(6, java.sql.Date.valueOf(employee.getDateOfBirth()));
    }
}

