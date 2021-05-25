package com.mastery.java.task.dao;

import com.mastery.java.task.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Employee create(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(long id);

    Employee update(long id, Employee employee);

    void delete(long id);
}
