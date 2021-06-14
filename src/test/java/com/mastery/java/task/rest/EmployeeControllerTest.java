package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;


    @Test
    public void findById() throws Exception {
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        when(employeeService.findById(1)).thenReturn(employee1);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")).andExpect(status().isOk());
    }

    @Test
    public void findAll() throws Exception{
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        when(employeeService.findAll()).thenReturn(list);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee")).andExpect(status().isOk());
    }

    @Test
    public void findByDepartmentId() throws Exception{
        Employee employee1 = new Employee(1, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        when(employeeService.findByDepartmentId(1)).thenReturn(list);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/department/1")).andExpect(status().isOk());
    }


    @Test
    public void deleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1" ))
                .andExpect(status().isOk());
    }

    @Test
    public void newEmployee() throws Exception{
        Employee employee1 = new Employee(5, "Ivan", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        doNothing().when(employeeService).newEmployee(employee1);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getEmployeeToJson(employee1));
        this.mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void updateEmployee() throws Exception{
        Employee employee1 = new Employee(1, "Artem", "Ivanov", 1, "line manager", Gender.MALE, LocalDate.of(1991,12,1));
        when(employeeService.findById(1)).thenReturn(employee1);
        doNothing().when(employeeService).updateEmployee(employee1,1);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getEmployeeToJson(employee1));

        this.mockMvc.perform(builder).andExpect(status().isOk());
    }


    public String getEmployeeToJson (Employee employee){
        return "{\"firstName\":\"" + employee.getFirstName() +
                "\", \"lastName\":\"" + employee.getLastName() +
                "\", \"departmentId\":\"" + employee.getDepartmentId() +
                "\", \"jobTitle\":\"" + employee.getJobTitle() +
                "\", \"gender\":\"" + employee.getGender() +
                "\", \"dateOfBirth\":\"" + employee.getDateOfBirth()+
                "\"}";
    }
}
