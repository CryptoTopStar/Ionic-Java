package com.smart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NamedQueries({
	@NamedQuery(name="findAllRoles",query="select role from Role role"),
	@NamedQuery(name="findByRoleName",query="select r from Role r where role=:roleName"),
})



@Entity
@Table(name="role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {


	@Column(name="role_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name")
	private String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}





}