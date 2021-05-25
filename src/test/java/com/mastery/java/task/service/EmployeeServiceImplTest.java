package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.exeption.EmployeeNotFoundException;
import com.mastery.java.task.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.mastery.java.task.model.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeService underTest;

    @MockBean
    EmployeeDao dao;

    static Employee e;
    static EmployeeDto eDto;

    @BeforeAll
    static void setUp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        e = new Employee(1, "Aliko", "Dangote", 1, "Data scientist", MALE,
                LocalDate.parse("02-03-1995", df).atStartOfDay());
        eDto = new EmployeeDto(1L, "Aliko", "Dangote", 1L, "Data scientist", "MALE",
                LocalDate.parse("02-03-1995", df).atStartOfDay());

    }

    @Test
    void shouldCorrectlyMapDtoToEntity() {
        underTest.create(eDto);

        Mockito.verify(dao).create(e);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        when(dao.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.findById(3));
    }
}
