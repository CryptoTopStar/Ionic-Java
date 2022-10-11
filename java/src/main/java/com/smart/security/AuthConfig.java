package com.smart.security;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.smart.model.Role;
import com.smart.model.User;

public class AuthConfig {

	public static ThreadLocal<User> currentUser = new ThreadLocal<>();
	
	
	
	public static String getUsername() {
		return currentUser.get().getUsername();
	}
	
	public static Long getUserId() {
		return currentUser.get().getId();
	}
	
	public static String userRoles() {
		return Optional.of(
				currentUser.get().getRoles()
				.stream()
				.map(role -> role.getName())
				.collect(Collectors.joining(","))
				)
				.orElse(null);
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		currentUser.remove();
	}
	
	
}
