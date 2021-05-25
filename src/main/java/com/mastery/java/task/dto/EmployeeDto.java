package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "First name cannot be empty")
    private String lastName;

    @Min(value = 0, message = "Department id has to be greater than 0")
    @NotNull(message= "Department id cannot be empty")
    private Long departmentId;

    @NotEmpty(message = "Job title cannot be empty")
    private String jobTitle;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateOfBirth;
}
