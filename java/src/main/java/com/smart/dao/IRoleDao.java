package com.smart.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smart.generic.IGenericDao;
import com.smart.model.Role;


@Repository
@Transactional(readOnly = true)
public interface IRoleDao extends IGenericDao<Role>{
	
	Role findById(Long id);

	Role findByName(String string);
	
}
