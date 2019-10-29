package de.fred4jupiter.spring.boot.jsf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="email")
public class Email {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="massage")
	private String massage;
	
	@Column(name="fromID")
	private int fromID;
	
	
	@Column(name="toID")
	private int toID;
	
	
	
	@Column(name="subject")
	private String subject;
	
	
	@Column(name="time_massage")
	private String timeMassage;
	
	@Column(name="star")
	private int star;
	
	@Column(name="status")
	private int status;
	
	@Transient
	private Employee fromEmployee;
	
	@Transient
	private String emailTo;
	
	@Transient
	private String fromName;
	
	@Transient
	private String fromEmail;
	
	@Column(name="star_color")
	private String starColor;
		
	// define constructors
	
	public Email() {  }



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getMassage() {
		return massage;
	}



	public void setMassage(String massage) {
		this.massage = massage;
	}



	public int getFromID() {
		return fromID;
	}



	public void setFromID(int fromID) {
		this.fromID = fromID;
	}



	public int getToID() {
		return toID;
	}



	public void setToID(int toID) {
		this.toID = toID;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getTimeMassage() {
		return timeMassage;
	}



	public void setTimeMassage(String timeMassage) {
		this.timeMassage = timeMassage;
	}



	public int getStar() {
		return star;
	}



	public void setStar(int star) {
		this.star = star;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public Employee getFromEmployee() {
		return fromEmployee;
	}



	public void setFromEmployee(Employee fromEmployee) {
		this.fromEmployee = fromEmployee;
	}



	public String getEmailTo() {
		return emailTo;
	}



	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}


	public String getStarColor() {
		return starColor;
	}

	public void setStarColor(String starColor) {
		this.starColor = starColor;
	}



	public String getFromName() {
		return fromName;
	}



	public void setFromName(String fromName) {
		this.fromName = fromName;
	}



	public String getFromEmail() {
		return fromEmail;
	}



	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}



	 
 
 		
}











