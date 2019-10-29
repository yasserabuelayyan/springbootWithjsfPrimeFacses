package de.fred4jupiter.spring.boot.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import de.fred4jupiter.spring.boot.jsf.bean.HelloWorldBean;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;
import de.fred4jupiter.spring.boot.jsf.scope.ScopeName;
import de.fred4jupiter.spring.boot.jsf.service.EmployeeService;


 
//@FacesValidator("de.fred4jupiter.spring.boot.jsf.validator.EmailValidator")
public class EmailValidator implements Validator{
 
	 
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;
	
	public EmailValidator(  ){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		
		if(value== null || value.toString().trim().equals("")) {
			FacesMessage msg = 
					new FacesMessage("E-mail validation failed.", 
							"Enter E-mail");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
		}
		else {
			matcher = pattern.matcher(value.toString());
			if(!matcher.matches()){
				
				FacesMessage msg = 
					new FacesMessage("E-mail validation failed.", 
							"Invalid E-mail format.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);

			}else {
				
			 
//				System.out.println(value.toString().trim());
//				
//				if(employeeService==null) {
//					System.out.println("employeeService is nulll");
//				}
//				Employee employee = employeeService.getUserByEmail(value.toString().trim());
//				if(employee!=null) {
//					FacesMessage msg = 
//							new FacesMessage("E-mail validation failed.", 
//									"E-mail is already exists");
//						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//						throw new ValidatorException(msg);
//				}
				
			}
		}
		
		
		

	}
}