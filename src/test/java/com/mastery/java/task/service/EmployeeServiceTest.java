package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;


public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDao employeeDao;


    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findById() {
        Integer id = 1;
        employeeService.findById(id);
        verify(employeeDao).findById(id);

    }

    @Test
    public void findAll() {
        employeeService.findAll();
        verify(employeeDao).findAll();
    }


    @Test
    public void findByDepartmentId() {
        Integer departmentId = 1;
        employeeService.findByDepartmentId(departmentId);
        verify(employeeDao).findByDepartmentId(departmentId);
    }

    @Test
    public void deleteEmployee() {
        Integer id = 1;
        employeeService.deleteEmployee(id);
        verify(employeeDao).deleteEmployee(id);
    }

    @Test
    public void newEmployee() {
        Employee employee1 = new Employee(5, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        employeeService.newEmployee(employee1);
        verify(employeeDao).newEmployee(employee1);
    }

    @Test
    public void updateEmployee() {
        Employee employee1 = new Employee(5, "Ivan", "Petrov", 1, "line manager", Gender.MALE, LocalDate.of(1991, 12, 1));
        employeeService.newEmployee(employee1);
        verify(employeeDao).newEmployee(employee1);
    }
}
