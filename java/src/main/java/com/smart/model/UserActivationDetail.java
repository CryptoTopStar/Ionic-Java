package com.smart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user_activation_detail")
@ToString
@Getter
@Setter
public class UserActivationDetail {

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="uad_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	
	@Column(name="activation_token")
	private String activationToken;
	
	@Column(name="token_expire_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpireOn;
	
	@Column(name="used_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usedOn;
	
	@Column(name="valid")
	private boolean valid;

}
