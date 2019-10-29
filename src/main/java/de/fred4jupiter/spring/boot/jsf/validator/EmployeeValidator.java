package de.fred4jupiter.spring.boot.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.stereotype.Component;
import de.fred4jupiter.spring.boot.jsf.bean.HelloWorldBean;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;

@Component
@FacesValidator("de.fred4jupiter.spring.boot.jsf.validator.EmployeeValidator")
public class EmployeeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		HelloWorldBean helloWorldBean = (HelloWorldBean) value;
		if (helloWorldBean.getPassword() == null || helloWorldBean.getPassword().trim().equals("")) {
			FacesMessage msg = new FacesMessage("Enter Password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		} else if (helloWorldBean.getUsername() == null || helloWorldBean.getUsername().trim().equals("")) {
			FacesMessage msg = new FacesMessage("Enter username");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		} else if (helloWorldBean.getUsername().trim().equalsIgnoreCase("y")) {
			FacesMessage msg = new FacesMessage("Enter username");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage("lgnFrm:loginMsg", msg);

		}
	}

}