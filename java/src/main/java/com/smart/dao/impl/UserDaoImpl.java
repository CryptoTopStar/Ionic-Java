package com.smart.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.dao.IUserDao;
import com.smart.dao.repo.IUserRepository;
import com.smart.generic.impl.GenericDaoImpl;
import com.smart.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {
	
	
	@Autowired
	IUserRepository repo;
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User findByUsername(String email) {
		return repo.findByEmail(email);
//		try{
//			Query q = getEntityManager().createNamedQuery("findByName")
//			.setParameter("userName", username);
//			return (User) q.getSingleResult();
//		}catch(Exception e){
//			LOGGER.error("no user found by name : {}", username);
//		}
	}


	@Override
	public User findByEmail(String email) {
		try{
			Query q = getEntityManager().createNamedQuery("findByEmail")
			.setParameter("email", email);
			return (User) q.getSingleResult();
		}catch(Exception ex){
			LOGGER.error("no user found by email : {}", email);
			return null;
		}
		
	}


	@Override
	public String enableUser(Long userId, Boolean enabled) {
		try{
			Query q = getEntityManager().createNamedQuery("enableUser")
			.setParameter("userId", userId)
			.setParameter("enabled", enabled);
			q.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return "success";
		
	}


	@Override
	public User findById(Long id) {
		return repo.findById(id).get();
	}


	@Override
	public User save(User user) {
		try{
			return repo.save(user);
		}catch(Exception ex){
			return null;
		}
	}
	
	@Override
	public boolean deleteUser(Long userId) {
		try{
			Query q = getEntityManager().createNamedQuery("deleteUser")
			.setParameter("userId", userId)
			.setParameter("deleted", true);
			q.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
		
	}


	@Override
	public Long count() {
		return repo.count();
	}


	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}


	@Override
	public List<User> findAll() {
		return repo.findAll();
	}


	@Override
	public User findByUsernameOrEamil(String username, String email) {
		return repo.findByUsernameOrEmail(username, email);
	}


	

	
}
