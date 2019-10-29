package de.fred4jupiter.spring.boot.jsf.service;

import java.util.List;

import de.fred4jupiter.spring.boot.jsf.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public void save(Employee theEmployee);

	public Employee login(String email, String password);

	public Employee EmployeeDetails(int id);

	public Employee findOne(int id);

	public Employee getUserByEmail(String email);

}
