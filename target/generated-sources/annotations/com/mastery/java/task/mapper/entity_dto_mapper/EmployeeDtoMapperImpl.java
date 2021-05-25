package com.mastery.java.task.mapper.entity_dto_mapper;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-25T15:47:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeDtoMapperImpl implements EmployeeDtoMapper {

    @Override
    public EmployeeDto mapToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployeeId( employee.getEmployeeId() );
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setDepartmentId( employee.getDepartmentId() );
        employeeDto.setJobTitle( employee.getJobTitle() );
        if ( employee.getGender() != null ) {
            employeeDto.setGender( employee.getGender().name() );
        }
        employeeDto.setDateOfBirth( employee.getDateOfBirth() );

        return employeeDto;
    }

    @Override
    public Employee mapToEntity(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        if ( dto.getEmployeeId() != null ) {
            employee.setEmployeeId( dto.getEmployeeId() );
        }
        employee.setFirstName( dto.getFirstName() );
        employee.setLastName( dto.getLastName() );
        if ( dto.getDepartmentId() != null ) {
            employee.setDepartmentId( dto.getDepartmentId() );
        }
        employee.setJobTitle( dto.getJobTitle() );
        if ( dto.getGender() != null ) {
            employee.setGender( Enum.valueOf( Gender.class, dto.getGender() ) );
        }
        employee.setDateOfBirth( dto.getDateOfBirth() );

        return employee;
    }

    @Override
    public void update(EmployeeDto dto, Employee employee) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getEmployeeId() != null ) {
            employee.setEmployeeId( dto.getEmployeeId() );
        }
        if ( dto.getFirstName() != null ) {
            employee.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            employee.setLastName( dto.getLastName() );
        }
        if ( dto.getDepartmentId() != null ) {
            employee.setDepartmentId( dto.getDepartmentId() );
        }
        if ( dto.getJobTitle() != null ) {
            employee.setJobTitle( dto.getJobTitle() );
        }
        if ( dto.getGender() != null ) {
            employee.setGender( Enum.valueOf( Gender.class, dto.getGender() ) );
        }
        if ( dto.getDateOfBirth() != null ) {
            employee.setDateOfBirth( dto.getDateOfBirth() );
        }
    }
}
