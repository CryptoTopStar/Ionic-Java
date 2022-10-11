package com.smart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Transaction {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "debited_from_id")
	private AccountDetails debitedFrom;
	
	
	@OneToOne
	@JoinColumn(name = "credited_to_id")
	private AccountDetails creditedTo;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TransectionStatus Status;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TransectionType type;
	
	@Column
	private String desc;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date transectionDate;
	
	
	@Column
	private Double transectionAmount;
	
	@OneToOne
	@JoinColumn(name = "marchent_id")
	private Marchent marchentId;
	
	
	
}
