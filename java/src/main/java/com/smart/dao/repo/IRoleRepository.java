package com.smart.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smart.model.Role;

@Repository
@Transactional(readOnly = true)
public interface IRoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
