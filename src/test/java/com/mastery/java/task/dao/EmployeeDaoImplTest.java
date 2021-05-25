package com.mastery.java.task.dao;

import com.mastery.java.task.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.mastery.java.task.model.Gender.MALE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:data.sql")
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao underTest;

    static Employee e;

    @BeforeAll
    static void setUp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        e = new Employee(1, "Aliko", "Dangote", 1, "Data scientist", MALE,
                        LocalDate.parse("02-03-1995", df).atStartOfDay());
    }

    @Test
    @Order(1)
    void shouldFindById() {
        Optional<Employee> expected = Optional.of(e);

        Optional<Employee> actual = underTest.findById(1);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    @Order(2)
    void shouldUpdate() {
        e.setFirstName("test");

        underTest.update(1, e);

        Optional<Employee> actual = underTest.findById(1);
        Assertions.assertEquals(e.getFirstName(), actual.get().getFirstName());
    }

    @Test
    @Order(3)
    void shouldDelete() {
        underTest.delete(1);

        Optional<Employee> actual = underTest.findById(1);
        Assertions.assertEquals(Optional.empty(), actual);
    }
}
