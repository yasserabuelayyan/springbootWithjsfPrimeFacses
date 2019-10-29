package de.fred4jupiter.spring.boot.jsf.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fred4jupiter.spring.boot.jsf.dao.EmailRepository;
import de.fred4jupiter.spring.boot.jsf.dao.EmployeeRepository;
import de.fred4jupiter.spring.boot.jsf.entity.Email;
import de.fred4jupiter.spring.boot.jsf.entity.Employee;

@Service
public class EmailServiceImpl implements EmailService {

	private EmailRepository emailRepository;

	@Autowired
	public EmailServiceImpl(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Override
	public List<Email> findAll() {
		// TODO Auto-generated method stub
		return emailRepository.findAll();
	}

	@Override
	public void save(Email email) {
		emailRepository.save(email);
		
	}

	@Override
	public Email findOne(int id) {
		// TODO Auto-generated method stub
		return emailRepository.findOne(id);
	}

	@Override
	public List<Email> getInbox(int toID) {
		// TODO Auto-generated method stub
		return emailRepository.getInbox(toID);
	}

	@Override
	public List<Email> getSend(int fromID) {
		// TODO Auto-generated method stub
		return emailRepository.getSend(fromID);
	}

	@Override
	public List<Email> getStar(int toID) {
		// TODO Auto-generated method stub
		return emailRepository.getStar(toID);
	}

	@Override
	public void changeStatus(int id) {
		emailRepository.changeStatus(id);
		
	}

	@Override
	public void addStar(int id) {
		emailRepository.addStar(id);
		
	}

	@Override
	public void removeStar(int id) {
		
		emailRepository.removeStar(id);
		
	}

	@Override
	public void removeEmail(int id) {
		emailRepository.removeEmail(id);
		
	}

 

	 

}
