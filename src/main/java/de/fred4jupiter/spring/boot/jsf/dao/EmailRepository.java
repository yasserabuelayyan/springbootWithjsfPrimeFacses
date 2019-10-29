package de.fred4jupiter.spring.boot.jsf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import de.fred4jupiter.spring.boot.jsf.entity.Email;
import de.fred4jupiter.spring.boot.jsf.entity.Employee; 

public interface EmailRepository extends JpaRepository<Email, Integer> {

	@Query(value = "SELECT * FROM email e WHERE e.toID = :toID", nativeQuery = true)
	List<Email> getInbox( @Param("toID") int toID);
	
	@Query(value = "SELECT * FROM email e WHERE e.fromID = :fromID", nativeQuery = true)
	List<Email> getSend( @Param("fromID") int fromID);
	
	@Query(value = "SELECT * FROM email e WHERE e.toID = :toID and e.star = 1", nativeQuery = true)
	List<Email> getStar( @Param("toID") int toID);
	
	@Query(value = "UPDATE email SET status = 1 WHERE id = ?1", nativeQuery = true)
	void changeStatus(int id);
	
	@Query(value = "UPDATE email SET star = 1 WHERE id = ?1", nativeQuery = true)
	void addStar(int id);
	
	@Query(value = "UPDATE email SET star = 0 WHERE id = ?1", nativeQuery = true)
	void removeStar(int id);
	@Transactional
	@Modifying
//	@Query(value = "delete from email u where u.id = :id", nativeQuery = true)
	//@Query(value = "DELETE FROM email WHERE id = ?1", nativeQuery = true)
	@Query("delete from Email e where e.id = ?1")
//    void removeEmail(@Param("id") int id);
	void removeEmail(int id);
 

}
