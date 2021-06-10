package com.mastery.java.task.rest;

import com.mastery.java.task.dao.EmployeeDao;
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

    @GetMapping("/department/{departmentid}")
    public List<Employee> findByDepartmentId(@PathVariable Integer departmentid) {
        return employeeService.findByDepartmentId(departmentid);
    }

    @GetMapping("/lastname/{lastname}")
    public Employee findByLastname (@PathVariable String lastname){return employeeService.findByLastname(lastname);}

    @DeleteMapping("/lastname/{lastname}")
    public void deleteEmployee (@PathVariable String lastname){employeeService.deleteEmployee(lastname);}

    @PostMapping()
    public void newEmployee (@RequestBody Employee newEmployee){ employeeService.newEmployee(newEmployee);}

    @PutMapping("/lastname/{lastname}")
    public void updateEmployee (@RequestBody Employee updatedEmployee, @PathVariable String lastname){
        Employee employee = employeeService.findByLastname(lastname);
        employee.setEmployeeId(updatedEmployee.getEmployeeId());
        employee.setDepartmentId(updatedEmployee.getDepartmentId());
        employee.setJobTitle(updatedEmployee.getJobTitle());
        employeeService.updateEmployee(employee, lastname);

    }

}
