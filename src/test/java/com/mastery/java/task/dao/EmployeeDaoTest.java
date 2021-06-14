package com.mastery.java.task.dao;


import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@JdbcTest
@Sql({"schema.sql", "resources/data.sql"})
public class EmployeeDaoTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    EmployeeDao employeeDao;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findById(){
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        assertEquals(employee1.getEmployeeId(), employeeDao.findById(1).getEmployeeId());

    }

//    @Test
//    public void findById() {
//        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
//        when(jdbcTemplate.queryForObject("SELECT * FROM employee WHERE employee_id = 1", Employee.class)).thenReturn(employee1);
//        Employee result = employeeDao.findById(1);
//        assertEquals(employee1.getEmployeeId(), result.getEmployeeId());
//    }



    @Test
    public void findAll() {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        Employee employee2 = new Employee(2, "Petr", "Petrov", 1, "tester", Gender.MALE, LocalDate.of(1991,11,1));
        Employee employee3 = new Employee(3, "Stepan", "Stepanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,10,1));
        Employee employee4 = new Employee(4, "Fiodr", "Fiodorov", 1, "tester", Gender.MALE, LocalDate.of(1991,9,1));

        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);

        when(jdbcTemplate.queryForList("SELECT * FROM employee", Employee.class)).thenReturn(list);
        List<Employee> result = employeeDao.findAll();
        assertEquals(result.get(0).getEmployeeId(), employee1.getEmployeeId());
        assertEquals(result.get(1).getEmployeeId(), employee2.getEmployeeId());
        assertEquals(result.get(2).getEmployeeId(), employee3.getEmployeeId());
        assertEquals(result.get(3).getEmployeeId(), employee4.getEmployeeId());

    }


    @Test
    public void findByDepartmentId() {
    }

    @Test
    public void deleteEmployee() {
    }

    @Test
    public void newEmployee() {
    }

    @Test
    public void updateEmployee() {
    }
}
