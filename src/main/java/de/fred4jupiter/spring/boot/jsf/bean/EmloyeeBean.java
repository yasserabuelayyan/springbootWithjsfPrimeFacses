package de.fred4jupiter.spring.boot.jsf.bean;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.fred4jupiter.spring.boot.jsf.entity.Email;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;
import de.fred4jupiter.spring.boot.jsf.scope.ScopeName;
import de.fred4jupiter.spring.boot.jsf.service.EmailService;
import de.fred4jupiter.spring.boot.jsf.service.EmployeeService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope(ScopeName.SESSION)
public class EmloyeeBean implements Serializable {

	/**
	 * 
	 */
	
	 public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	        
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	 }
	private static final long serialVersionUID = 1L;

	private Employee employee;
	
	private Employee selectEmployee;
	
	private List<Employee> employees;
	private List<Employee> filteredEmployee;

	private EmployeeService employeeService;


    public void onRowEdit(RowEditEvent event) {
    	Employee ee = (Employee) event.getObject();
    	System.out.println(ee.getFirstName());
    }
     
    public void onRowCancel(RowEditEvent event) {
        
    }

	public EmloyeeBean(@Autowired EmployeeService employeeService) {

		this.employeeService = employeeService;

		employees = employeeService.findAll();
		employee = new Employee();
	}

	public String employeeDetails() {

		final FacesContext fc = FacesContext.getCurrentInstance();
		final Map<String, String> params = (Map<String, String>) fc.getExternalContext().getRequestParameterMap();
		employee = employeeService.findOne(Integer.valueOf(params.get("emploueeIDParam")));

		return "details";
	}

	public String addEmployee() {

		String goTo = "";
		if (this.employee != null) {

			if (employee.getFirstName() == null || employee.getFirstName().trim().equals("")) {

				goTo = "add-Employee-Page";
			} else if (employee.getLastName() == null || employee.getLastName().trim().equals("")) {
				goTo = "add-Employee-Page";
			} else if (employee.getEmail() == null || employee.getEmail().trim().equals("")) {
				goTo = "add-Employee-Page";
			} else if (employee.getPassword() == null || employee.getPassword().trim().equals("")) {
				goTo = "add-Employee-Page";
			} else if (employee.getPassword().trim().length() < 8) {
				goTo = "add-Employee-Page";
			} else {
				// employeeService.save(this.employee);
				// this.employees = employeeService.findAll();

				goTo = "employees-Page";
			}

		} else {
			goTo = "add-Employee-Page";
		}
		return goTo;
	}

	public String addEmployeePage() {

		this.employee = new Employee();
		return "add-Employee-Page";
	}

	public String editEmployee() {

		final FacesContext fc = FacesContext.getCurrentInstance();
		final Map<String, String> params = (Map<String, String>) fc.getExternalContext().getRequestParameterMap();
		this.employee = employeeService.findOne(Integer.valueOf(params.get("emploueeIDParam")));
		if (employee == null) {
			System.out.println("nullllllllll");
		}
		return "edit-employee-Page";
	}

	public String editEmployeePage() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final Map<String, String> params = (Map<String, String>) fc.getExternalContext().getRequestParameterMap();
		Employee dbEmployee = employeeService.findOne(Integer.valueOf(params.get("emploueeIDParam")));

		dbEmployee.setFirstName(employee.getFirstName());
		dbEmployee.setLastName(employee.getLastName());
		dbEmployee.setEmail(employee.getEmail());

		employeeService.save(dbEmployee);

		this.employees = employeeService.findAll();
		return "employees-Page";

	}

	public String ListEmployee() {

		return "employees-Page";
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getFilteredEmployee() {
		return filteredEmployee;
	}

	public void setFilteredEmployee(List<Employee> filteredEmployee) {
		this.filteredEmployee = filteredEmployee;
	}

	public Employee getSelectEmployee() {
		return selectEmployee;
	}

	public void setSelectEmployee(Employee selectEmployee) {
		this.selectEmployee = selectEmployee;
	}

}
