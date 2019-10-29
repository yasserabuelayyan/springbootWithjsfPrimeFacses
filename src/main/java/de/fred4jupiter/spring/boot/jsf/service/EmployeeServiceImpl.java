package de.fred4jupiter.spring.boot.jsf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fred4jupiter.spring.boot.jsf.dao.EmployeeRepository;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

//	@Override
//	public Employee findById(int theId) {
//		
//		employeeRepository.f
//		Optional<Employee> result = employeeRepository.findById(theId);
//		
//		Employee theEmployee = null;
//		
//		if (result.isPresent()) {
//			theEmployee = result.get();
//		} 
//	 	
//		return theEmployee;
//	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

 

	 

	@Override
	public Employee login(String email , String password) {
		
		return  employeeRepository.login(email,password);
	}

	@Override
	public Employee EmployeeDetails(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findOne(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findOne(id);
	}

	@Override
	public Employee getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return employeeRepository.getUserByEmail(email);
	}

//	@Override
//	public void deleteById(int theId) {
//		employeeRepository.deleteById(theId);
//	}

}
