package de.fred4jupiter.spring.boot.jsf.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import de.fred4jupiter.spring.boot.jsf.entity.Email;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;
import de.fred4jupiter.spring.boot.jsf.scope.ScopeName;
import de.fred4jupiter.spring.boot.jsf.service.EmailService;
import de.fred4jupiter.spring.boot.jsf.service.EmployeeService;
import de.fred4jupiter.spring.boot.jsf.utile.SpringBootJSF;

import java.util.ArrayList;
import java.util.Date;
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
public class HelloWorldBean {

	private EmployeeService employeeService;
	private EmailService emailService;

	public HelloWorldBean(@Autowired EmployeeService employeeService, @Autowired EmailService emailService) {
		this.emailService = emailService;
		this.employeeService = employeeService;
		contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		this.email = new Email();
		checkCookie();

	}

	
	
	 
	public String goToEmailSend() {

		sends = emailService.getSend(employee.getId());
		if (!sends.isEmpty()) {

			for (Email email : sends) {
				email.setFromEmployee(employeeService.findOne(email.getToID()));
			}
		} else {
			sends = new ArrayList<>();
		}
		return "email-send";
	}
	
	public String removeEmail(int id) {
		 
		emailService.removeEmail(id);
		 
	return "";	
	}
	public String goToReadEmail(int id) {
 
	System.out.println(id);
	email = emailService.findOne(id);
	if(email.getStatus()==0) {
		email.setStatus(1);
		emailService.save(email);
	}
	
	Employee employeefrom = employeeService.findOne(email.getFromID());
	System.out.println(employeefrom.getFirstName());
	email.setFromEmail(employeefrom.getEmail());
	email.setFromName(employeefrom.getFirstName()+" "+ employeefrom.getLastName());
	
	
	return "email-read";
	
	}
	

	public String goToEmailStarred() {

		System.out.println("work ststs");
		stars = emailService.getStar(employee.getId());
		if (!stars.isEmpty()) {

			for (Email email : stars) {
				email.setFromEmployee(employeeService.findOne(email.getFromID()));
			}
		} else {
			stars = new ArrayList<>();
		}
		return "email-starred";
	}

	public String goToEmailInbox() {

		this.inboxsNumber = 0;
		

		inboxs = emailService.getInbox(employee.getId());
		if (!inboxs.isEmpty()) {

			for (Email email : inboxs) {
				email.setFromEmployee(employeeService.findOne(email.getFromID()));
				if (email.getStatus() == 0) {
					inboxsNumber++;
				}
			}
		} else {
			inboxs = new ArrayList<>();
		}
		return "email-inbox";
	}

	 

	public String goToEmailCompose() {
		email = new Email();
		return "email-compose";
	}

 

	public String starStatus(int emailID) {

		Email emailStar = emailService.findOne(emailID);
		emailStar.setStar((emailStar.getStar() == 1 ? 0 : 1));
		emailStar.setStarColor((emailStar.getStar() == 1 ? "gold" : ""));
		emailService.save(emailStar);
		stars = emailService.getStar(employee.getId());
		if (!stars.isEmpty()) {

			for (Email email : stars) {
				email.setFromEmployee(employeeService.findOne(email.getFromID()));

			}

		} else {
			stars = new ArrayList<>();
		}
		this.inboxsNumber = 0;

		inboxs = null;
		this.inboxs = emailService.getInbox(employee.getId());
		if (!inboxs.isEmpty()) {

			for (Email email : inboxs) {
				email.setFromEmployee(employeeService.findOne(email.getFromID()));
				// email.setStarColor((email.getStar() == 1 ? "gold" : ""));
				if (email.getStatus() == 0) {
					inboxsNumber++;
				}
			}
		} else {
			inboxs = new ArrayList<>();
		}
		return "email-inbox";
	}

	public String composeEmail() {
		FacesContext context = FacesContext.getCurrentInstance();

		Employee employeeCheck = employeeService.getUserByEmail(this.email.getEmailTo().trim());

		if (employeeCheck != null) {

			if (employee.getId() != employeeCheck.getId()) {
				String currentDate = SpringBootJSF.dateToString(new Date());
				email.setTimeMassage(currentDate);
				email.setFromID(employee.getId());
				email.setToID(employeeCheck.getId());
				emailService.save(email);

				this.inboxsNumber = 0;

				inboxs = emailService.getInbox(employee.getId());
				if (!inboxs.isEmpty()) {
					for (Email email : inboxs) {
						email.setFromEmployee(employeeService.findOne(email.getFromID()));
						if (email.getStatus() == 0) {
							inboxsNumber++;
						}
					}
				} else {
					inboxs = new ArrayList<>();
				}

				return "email-inbox";
			} else {
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("Enter valid email address");
				message.setDetail("Enter valid email address");
				context.addMessage("ComposeForm:ErrorMsg", message);
				return "";
			}
		} else {

			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("email address not found");
			message.setDetail("email address not found");
			context.addMessage("ComposeForm:ErrorMsg", message);
			return "";

			// not found email address
		}

	}

	private Employee employee;
	private String contextPath;
	private String username;
	private String password;
	private boolean remember;
	private String remember1 = "";

	// email
	private List<Email> inboxs;
	private int inboxsNumber;
	private List<Email> sends;
	private List<Email> stars;
	private Email email;

	private final static Map<String, Object> languages = new HashMap<>();
	private String locale;
	static {
		HelloWorldBean.languages.put("arabic", new Locale("ar"));
		HelloWorldBean.languages.put("english", Locale.ENGLISH);
	}

	
	public String employees() {
		
		
		
      return "employees-Page";
	}
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		employee = employeeService.login(this.username, this.password);

		String goTo = "";
		if (employee == null) {

			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("invalid Username or Password");
			message.setDetail("invalid Username details");
			context.addMessage("lgnFrm:loginMsg", message);
			return "";
		}
		goTo = "employees-Page";
		Cookie btuser = new Cookie("btuser", username);
		Cookie btpasswd = new Cookie("btpasswd", password);

		remember1 = remember ? "true" : "false";

		Cookie btremember = new Cookie("btremember", remember1);
		btuser.setMaxAge(3600);
		btpasswd.setMaxAge(3600);

		((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(btuser);
		((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(btpasswd);
		((HttpServletResponse) context.getExternalContext().getResponse()).addCookie(btremember);

		return goTo;

	}

	public void checkCookie() {

		String cookieName = null;

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Cookie[] cookie = httpServletRequest.getCookies();

		if ((cookie != null) && (cookie.length > 0)) {
			for (int i = 0; i < cookie.length; i++) {
				cookieName = cookie[i].getName();

				if (cookieName.equals("btuser")) {
					username = cookie[i].getValue();
				} else if (cookieName.equals("btpasswd")) {
					password = cookie[i].getValue();
				} else if (cookieName.equals("btremember")) {
					remember1 = cookie[i].getValue();
					remember = remember1.equals("false") ? false : true;

				}
			}

		}

	}

	public void changelanguage(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		System.out.println(newLocaleValue);

		for (Map.Entry<String, Object> entry : languages.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}

	}

	public String goToLogin() {
		return "index";
	}

	public String logout() {

		return "index";
	}

	public String goToEmployees() {
		return "employees-Page";
	}

	public String goToprofile() {

		return "profile";
	}

	public Map<String, Object> getLanguages() {
		return HelloWorldBean.languages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getRemember1() {
		return remember1;
	}

	public void setRemember1(String remember1) {
		this.remember1 = remember1;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Email> getInboxs() {
		return inboxs;
	}

	public void setInboxs(List<Email> inboxs) {
		this.inboxs = inboxs;
	}

	public List<Email> getSends() {
		return sends;
	}

	public void setSends(List<Email> sends) {
		this.sends = sends;
	}

	public List<Email> getStars() {
		return stars;
	}

	public void setStars(List<Email> stars) {
		this.stars = stars;
	}

	public int getInboxsNumber() {
		return inboxsNumber;
	}

	public void setInboxsNumber(int inboxsNumber) {
		this.inboxsNumber = inboxsNumber;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
