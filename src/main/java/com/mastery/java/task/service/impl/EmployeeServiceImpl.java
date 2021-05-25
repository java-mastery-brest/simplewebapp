package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.exeption.EmployeeNotFoundException;
import com.mastery.java.task.mapper.entity_dto_mapper.EmployeeDtoMapper;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeDtoMapper mapper;


    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao,
                               EmployeeDtoMapper mapper) {
        this.employeeDao = employeeDao;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = mapper.mapToEntity(employeeDto);

        return mapper.mapToDto(employeeDao.create(employee));
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeDao.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(long id) {
        return mapper.mapToDto(
                employeeDao.findById(id)
                        .<EmployeeNotFoundException>orElseThrow(
                                () -> new EmployeeNotFoundException(id)));
    }

    @Override
    public EmployeeDto update(long id, EmployeeDto employeeDto) {
        employeeDao.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee employee = mapper.mapToEntity(employeeDto);

        return mapper.mapToDto(employeeDao.update(id, employee));
    }

    @Override
    public EmployeeDto partialUpdate(long id, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        optionalEmployee
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee employee = optionalEmployee.get();
        mapper.update(employeeDto, employee);

        return mapper.mapToDto(employeeDao.update(id, employee));
    }

    @Override
    public void delete(long id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        optionalEmployee
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeDao.delete(id);
    }
}
