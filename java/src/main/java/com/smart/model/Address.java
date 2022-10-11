package com.smart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter 
@Table
@ToString
@EqualsAndHashCode
public class Address {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String country;
	
	@Column
	private String state;
	
	@Column
	private String city;
	
	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@Column
	private String postalCode;
	
}
