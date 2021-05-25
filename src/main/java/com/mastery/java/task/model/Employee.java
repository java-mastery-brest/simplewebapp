package com.mastery.java.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private long employeeId;
    private String firstName;
    private String lastName;
    private long departmentId;
    private String jobTitle;
    private Gender gender;
    private LocalDateTime dateOfBirth;

    public Employee setId(long employeeId) {
        this.employeeId = employeeId;
        return this;
    }
}
