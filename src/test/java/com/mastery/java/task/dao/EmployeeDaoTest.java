package com.mastery.java.task.dao;


import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:data.sql"})
@RunWith(SpringRunner.class)
@Import(EmployeeDao.class)
//@SpringBootTest
public class EmployeeDaoTest {


    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findById() {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        assertEquals(employee1.getEmployeeId(), employeeDao.findById(1).getEmployeeId());
    }


    @Test
    public void findAll() {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        Employee employee2 = new Employee(2, "Petr", "Petrov", 1, "tester", Gender.MALE, LocalDate.of(1991, 11, 1));
        Employee employee3 = new Employee(3, "Stepan", "Stepanov", 2, "line manager", Gender.MALE, LocalDate.of(1991, 10, 1));
        Employee employee4 = new Employee(4, "Fiodr", "Fiodorov", 2, "tester", Gender.MALE, LocalDate.of(1991, 9, 1));

        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);

        List<Employee> result = employeeDao.findAll();
        assertEquals(result.get(0).getEmployeeId(), employee1.getEmployeeId());
        assertEquals(result.get(1).getEmployeeId(), employee2.getEmployeeId());
        assertEquals(result.get(2).getEmployeeId(), employee3.getEmployeeId());
        assertEquals(result.get(3).getEmployeeId(), employee4.getEmployeeId());

    }


    @Test
    public void findByDepartmentId() {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        Employee employee2 = new Employee(2, "Petr", "Petrov", 1, "tester", Gender.MALE, LocalDate.of(1991, 11, 1));
        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);

        List<Employee> result = employeeDao.findByDepartmentId(1);
        assertEquals(result.get(0).getEmployeeId(), employee1.getEmployeeId());
        assertEquals(result.get(1).getEmployeeId(), employee2.getEmployeeId());
    }

    @Test
    public void deleteEmployee() {
        employeeDao.deleteEmployee(1);
        assertEquals(3, employeeDao.findAll().size());

    }

    @Test
    public void newEmployee() {
        Employee employee1 = new Employee(5, "Artem", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        employeeDao.newEmployee(employee1);

        assertEquals(employee1.getEmployeeId(), employeeDao.findById(5).getEmployeeId());
    }

    @Test
    public void updateEmployee() {
        Employee employee1 = new Employee(1, "Artem", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        employeeDao.updateEmployee(employee1, 1);

        assertEquals(employee1.getFirstName(), employeeDao.findById(1).getFirstName());
    }
}
