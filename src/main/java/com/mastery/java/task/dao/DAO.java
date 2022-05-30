package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> getEmployee(Long id);

    List<T> getEmployees();

    void deleteEmployee(Long id);

    void updateEmployee(Employee employee, Long id);

    void insertEmployee(T t);
}
