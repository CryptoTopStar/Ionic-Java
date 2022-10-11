package com.smart.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smart.generic.IGenericDao;
import com.smart.model.UserActivationDetail;


@Repository
@Transactional(readOnly = true)
public interface IUserActivationDetailDao extends IGenericDao<UserActivationDetail> {

	UserActivationDetail findByToken(String token);
	UserActivationDetail findByUserId(Long userId);
}
