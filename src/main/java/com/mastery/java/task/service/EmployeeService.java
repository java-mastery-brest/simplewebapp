package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees(){
        return employeeDao.getEmployees();
    }

    public Optional<Employee> getEmployee(Long id){
        return employeeDao.getEmployee(id);
    }

    public void deleteEmployee(Long id){
        employeeDao.deleteEmployee(id);
    }

    public void updateEmployee(Employee employee, Long id){
        employeeDao.updateEmployee(employee, id);
    }

    public void insertEmployee(Employee employee){
        employeeDao.insertEmployee(employee);
    }

}
