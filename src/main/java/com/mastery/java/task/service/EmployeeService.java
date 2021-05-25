package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto create(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    EmployeeDto findById(long id);

    EmployeeDto update(long id, EmployeeDto employeeDto);

    EmployeeDto partialUpdate(long id, EmployeeDto employeeDto);

    void delete(long id);
}
