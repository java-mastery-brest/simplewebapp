package com.mastery.java.task.exeption;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(long id) {
        super(String.format("No employee with id = %d found", id));
    }
}
