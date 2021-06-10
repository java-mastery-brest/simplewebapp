package com.mastery.java.task.dao;

import com.mastery.java.task.config.DataBaseHandler;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;


@Component
public class EmployeeDao {

    private DataBaseHandler dataBaseHandler = new DataBaseHandler();

    public Employee findById (Integer id) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM employee WHERE employee_id = ?");
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            Employee employee = null;
            while (resultSet.next()){
                Integer employee_id = resultSet.getInt(1);
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                Integer department_id = resultSet.getInt(4);
                String job_title = resultSet.getString(5);
                String gender = resultSet.getString(6);
                Gender genderEnum = null;
                if (gender.equals("Male")){
                    genderEnum = MALE;
                } else if(gender.equals("Female")){
                    genderEnum = FEMALE;
                }
                LocalDate date_of_birth = resultSet.getDate(7).toLocalDate();

                employee = new Employee(employee_id, first_name, last_name, department_id, job_title, genderEnum, date_of_birth);
            }
            selectStatement.close();
            return employee;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> findAll() {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM employee ");
            ResultSet resultSet = selectStatement.executeQuery();
            Employee employee = null;
            List <Employee> list= new ArrayList<Employee>();
            while (resultSet.next()) {
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
                list.add(employee);
            }
            selectStatement.close();
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee findByLastname(String lastname) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM employee WHERE last_name = ?");
            selectStatement.setString(1, lastname);
            ResultSet resultSet = selectStatement.executeQuery();
            Employee employee = null;
            while (resultSet.next()){
                Integer employee_id = resultSet.getInt(1);
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                Integer department_id = resultSet.getInt(4);
                String job_title = resultSet.getString(5);
                String gender = resultSet.getString(6);
                Gender genderEnum = null;
                if (gender.equals("Male")){
                    genderEnum = MALE;
                } else if(gender.equals("Female")){
                    genderEnum = FEMALE;
                }
                LocalDate date_of_birth = resultSet.getDate(7).toLocalDate();

                employee = new Employee(employee_id, first_name, last_name, department_id, job_title, genderEnum, date_of_birth);
            }
            selectStatement.close();
            return employee;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;

    }

    public List<Employee> findByDepartmentId(Integer departmentId) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM employee WHERE department_id = ?");
            selectStatement.setInt(1, departmentId);
            ResultSet resultSet = selectStatement.executeQuery();
            Employee employee = null;
            List <Employee> list= new ArrayList<Employee>();
            while (resultSet.next()) {
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
                list.add(employee);
            }
            selectStatement.close();
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteEmployee(String lastname) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("DELETE FROM employee WHERE last_name = ?");
            selectStatement.setString(1, lastname);
            selectStatement.executeUpdate();
            selectStatement.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newEmployee(Employee newEmployee) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement("INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            Gender genderEnum = newEmployee.getGender();
            String stringGender = "";
            if (genderEnum == MALE){
                stringGender = "Male";
            } else if( genderEnum == FEMALE){
                stringGender = "Female";
            }

            selectStatement.setInt(1, newEmployee.getEmployeeId());
            selectStatement.setString(2, newEmployee.getFirstName());
            selectStatement.setString(3, newEmployee.getLastName());
            selectStatement.setInt(4, newEmployee.getDepartmentId());
            selectStatement.setString(5, newEmployee.getJobTitle());
            selectStatement.setString(6, stringGender);
            selectStatement.setDate(7, java.sql.Date.valueOf(newEmployee.getDateOfBirth()));
            selectStatement.executeUpdate();
            selectStatement.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee, String lastname) {
        try (Connection connection = dataBaseHandler.getDbCon()) {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "UPDATE employee SET employee_id = ?, department_id = ?, job_title = ? " +
                            "WHERE last_name = ?");
            selectStatement.setInt(1, employee.getEmployeeId());
            selectStatement.setInt(2, employee.getDepartmentId());
            selectStatement.setString(3, employee.getJobTitle());
            selectStatement.setString(4, lastname);
            selectStatement.executeUpdate();
            selectStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

