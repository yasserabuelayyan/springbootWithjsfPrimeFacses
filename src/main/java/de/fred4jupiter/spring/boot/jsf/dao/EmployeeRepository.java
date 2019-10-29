package de.fred4jupiter.spring.boot.jsf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.fred4jupiter.spring.boot.jsf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT * FROM employee e WHERE e.email = ?1 and e.password = ?2", nativeQuery = true)
	Employee login(String email, String password);

	@Query(value = "SELECT * FROM employee e WHERE e.id = ?1", nativeQuery = true)
	Employee EmployeeDetails(int id);

	@Query(value = "SELECT * FROM employee e WHERE e.email = ?1", nativeQuery = true)
	public Employee getUserByEmail(String email);

}
