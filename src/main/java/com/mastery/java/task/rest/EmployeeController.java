package com.mastery.java.task.rest;


import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }


    @GetMapping()
    public List<Employee> findAll (){return  employeeService.findAll();}


    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable Integer departmentId) {
        return employeeService.findByDepartmentId(departmentId);
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee (@PathVariable Integer id){employeeService.deleteEmployee(id);}


    @PostMapping()
    public void newEmployee (@RequestBody Employee newEmployee){ employeeService.newEmployee(newEmployee);}


    @PutMapping("/{id}")
    public void updateEmployee (@RequestBody Employee updatedEmployee, @PathVariable Integer id){
        Employee employee = employeeService.findById(id);
        employee.setDepartmentId(updatedEmployee.getDepartmentId());
        employee.setJobTitle(updatedEmployee.getJobTitle());
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setGender(updatedEmployee.getGender());
        employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        employeeService.updateEmployee(employee, id);
    }
}
