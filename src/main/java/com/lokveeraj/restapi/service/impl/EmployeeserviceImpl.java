package com.lokveeraj.restapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lokveeraj.restapi.dto.EmployeeDto;
import com.lokveeraj.restapi.entity.Employee;
import com.lokveeraj.restapi.exception.ResourseNotFoundException;
import com.lokveeraj.restapi.mapper.EmployeeMapper;
import com.lokveeraj.restapi.repository.EmployeeRepository;
import com.lokveeraj.restapi.service.EmployeeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeserviceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourseNotFoundException("Employee is not exisst with given id : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long emplyeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(emplyeeId).orElseThrow(
                () -> new ResourseNotFoundException("Employee is not exist with given id: " + emplyeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourseNotFoundException("Employee is not exist with given id: " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
