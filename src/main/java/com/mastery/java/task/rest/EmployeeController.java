package com.mastery.java.task.rest;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Value("${my.host}")
    private String host;

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> create(
            @RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto employeeDtoToReturn = service.create(employeeDto);
        return ResponseEntity
                .created(URI.create(host + "/employees/"
                        + employeeDtoToReturn.getEmployeeId()))
                .body(employeeDtoToReturn);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable long id,
            @RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(service.update(id, employeeDto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> partialUpdate(
            @PathVariable long id,
            @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(service.partialUpdate(id, employeeDto));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}