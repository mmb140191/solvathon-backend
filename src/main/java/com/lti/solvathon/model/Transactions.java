package com.lti.solvathon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions {
	
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
	private String transactionType;
	private double amount;
	
	
	@ManyToOne
    private BankInformation bank;
 
	
	public BankInformation getBank() {
		return bank;
	}
	public void setBank(BankInformation bank) {
		this.bank = bank;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
