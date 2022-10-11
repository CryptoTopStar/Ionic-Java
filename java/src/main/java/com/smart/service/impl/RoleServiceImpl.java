package com.smart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.IRoleDao;
import com.smart.model.Role;
import com.smart.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	IRoleDao dao;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Role> findAllRoles() {
		LOGGER.info("inside RoleServiceImpl @method findAllRole entry...");
		try {
			 return dao.findAll();
			 
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("inside RoleServiceImpl @method findAllRole ex",e.getLocalizedMessage());
		}
		return null;
	}
	
	

}
