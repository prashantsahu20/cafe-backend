package com.cts.service;

import com.cts.entities.Employee;
import com.cts.exception.ResourceNotFoundException;
import com.cts.repository.EmployeeRepository;
import com.cts.serviceimpl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setPhone("1234567890");
        employee.setAddress("123 Street");
        employee.setPassword("Password1!");
        employee.setSalary(50000.0);
        employee.setRole("ROLE_EMPLOYEE");
    }

    @Test
    public void testSaveEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(encoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("encodedPassword", savedEmployee.getPassword());
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(encoder, times(1)).encode(any(CharSequence.class));
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> foundEmployees = employeeService.getAllEmployees();

        assertNotNull(foundEmployees);
        assertEquals(1, foundEmployees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(employee.getId());

        assertNotNull(foundEmployee);
        assertEquals(employee.getName(), foundEmployee.getName());
        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(employee.getId());
        });

        verify(employeeRepository, times(1)).findById(employee.getId());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(encoder.encode(any(CharSequence.class))).thenReturn("encodedUpdatedPassword");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Jane Doe");
        updatedEmployee.setEmail("jane.doe@example.com");
        updatedEmployee.setPhone("0987654321");
        updatedEmployee.setAddress("456 Street");
        updatedEmployee.setPassword("NewPassword1!");
        updatedEmployee.setSalary(60000.0);
        updatedEmployee.setRole("ROLE_MANAGER");

        Employee result = employeeService.updateEmployee(employee.getId(), updatedEmployee);

        assertNotNull(result);
        assertEquals("encodedUpdatedPassword", result.getPassword());
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(encoder, times(1)).encode(any(CharSequence.class));
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeRepository.existsById(employee.getId())).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(employee.getId());

        employeeService.deleteEmployee(employee.getId());

        verify(employeeRepository, times(1)).existsById(employee.getId());
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }
}
