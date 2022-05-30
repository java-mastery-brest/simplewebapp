package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/getEmployees")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/getEmployee/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable("employeeId") Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/insertEmployee")
    public void insertEmployee(@RequestBody Employee employee){
        employeeService.insertEmployee(employee);
    }

    @DeleteMapping( "/deleteEmployee/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable("employeeId") Long id){
        employeeService.updateEmployee(employee, id);
    }
}
