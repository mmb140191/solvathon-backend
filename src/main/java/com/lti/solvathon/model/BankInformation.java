package com.lti.solvathon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BankInformation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String cardNo;
    private Integer expiryMonth;
    private Integer expiryYear;
    private String mobileNo;
    private boolean cardLossOrStolen;
    private String accountNumber;
    private double balance;

    private byte[] cvvToken;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="bank_id")
    private List<Transactions> transactions = new ArrayList<Transactions>();
 

    public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public byte[] getCvvToken() {
        return cvvToken;
    }

    public void setCvvToken(byte[] cvvToken) {
        this.cvvToken = cvvToken;
    }

	public boolean isCardLossOrStolen() {
		return cardLossOrStolen;
	}

	public void setCardLossOrStolen(boolean cardLossOrStolen) {
		this.cardLossOrStolen = cardLossOrStolen;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
    
	
    
}
