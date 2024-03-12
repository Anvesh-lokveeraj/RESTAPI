package com.lokveeraj.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokveeraj.restapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
