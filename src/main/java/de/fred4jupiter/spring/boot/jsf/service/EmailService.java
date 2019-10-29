package de.fred4jupiter.spring.boot.jsf.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import de.fred4jupiter.spring.boot.jsf.entity.Email;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;

public interface EmailService {

	public List<Email> findAll();

	public void save(Email email);

	public Email findOne(int id);

	List<Email> getInbox(int toID);

	List<Email> getSend(int fromID);

	List<Email> getStar(int toID);

	void changeStatus(int id);

	void addStar(int id);
	void removeStar(int id);
	void removeEmail(int id);

}
