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
import javax.persistence.ManyToOne;
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
public class CustomerCards {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "currency")
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column
	private String cardNumber;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo; 
	
	
}
