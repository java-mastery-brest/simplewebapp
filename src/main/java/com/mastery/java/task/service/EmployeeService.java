package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getEmployees(){
        return List.of(
                new Employee(
                        1L,
                        "Adam",
                        "Pietrasik",
                        1L,
                        "Software developer"
                )
        );
    }

}
