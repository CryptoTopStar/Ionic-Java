package com.smart.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smart.model.User;

@Repository
@Transactional(readOnly = true)
public interface IUserRepository extends JpaRepository<User, Long> {
	
	User findByUsernameOrEmail(String username,String email);
	
	User findByEmail(String email);

}
