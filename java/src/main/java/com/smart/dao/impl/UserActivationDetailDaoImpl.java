package com.smart.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.dao.IUserActivationDetailDao;
import com.smart.dao.repo.IUserActivationDetailRepository;
import com.smart.generic.impl.GenericDaoImpl;
import com.smart.model.UserActivationDetail;

@Repository
public class UserActivationDetailDaoImpl extends GenericDaoImpl<UserActivationDetail> implements IUserActivationDetailDao{

	@Autowired
	IUserActivationDetailRepository repo;
	
	
	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public UserActivationDetail findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public UserActivationDetail save(UserActivationDetail entity) {
		return repo.save(entity);
	}

	@Override
	public List<UserActivationDetail> findAll() {
		return repo.findAll();
	}

	@Override
	public UserActivationDetail findByToken(String token) {
		return repo.findByActivationTokenAndValidTrue(token);
	}

	@Override
	public UserActivationDetail findByUserId(Long userId) {
		return repo.findByUserId(userId);
	}

}
