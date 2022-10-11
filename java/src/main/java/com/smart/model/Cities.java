package com.smart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Cities{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
	
	@Column
	private boolean flag;
	
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Countries country;
	
	
	@OneToOne
	@JoinColumn(name = "state_id")
	private States state;
}
