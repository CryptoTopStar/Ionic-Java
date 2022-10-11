package com.smart.service;

import java.util.Map;

import com.smart.model.LoginUser;
import com.smart.model.User;
import com.smart.model.UserActivationDetail;

public interface IMailService {
	
	
	void sendActivationEmail(User user) throws Exception;

	void sendForgetPasswordEmail(UserActivationDetail uad, LoginUser user);

	String getactivateUserResponse(String msg);
}
