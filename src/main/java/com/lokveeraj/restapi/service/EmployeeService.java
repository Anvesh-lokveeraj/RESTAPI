package com.lokveeraj.restapi.service;

import java.util.List;

import com.lokveeraj.restapi.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long emplyeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);
}
