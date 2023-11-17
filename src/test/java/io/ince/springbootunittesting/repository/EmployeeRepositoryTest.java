package io.ince.springbootunittesting.repository;

import io.ince.springbootunittesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = Employee.builder()
                .firstName("Tutku")
                .lastName("Ince")
                .email("tince@mail.com")
                .build();

        employee2 = Employee.builder()
                .firstName("Utku")
                .lastName("Ince")
                .email("uince@mail.com")
                .build();

    }

    // JUnit test for save employee operation
    @DisplayName("Save Employee Operation")
    @Test
    void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
        Employee employee = employee1;

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("Find All Employees Operation")
    @Test
    void givenEmployeeList_whenFindAll_thenReturnEmployeeList() {
        // given - precondition or setup
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employeeRepository.saveAll(employees);

        // when - action or the behaviour that we are going test
        List<Employee> savedEmployeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(savedEmployeeList).isNotNull();
        assertThat(savedEmployeeList.size()).isEqualTo(2);
    }

    @DisplayName("Find Employee By Id Operation")
    @Test
    void givenEmployee_whenFindById_thenReturnEmployee() {
        // given - precondition or setup
        Employee employee = employee1;
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee dBEmployee = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(dBEmployee).isNotNull();
    }
}