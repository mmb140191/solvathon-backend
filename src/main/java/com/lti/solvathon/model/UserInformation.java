package com.lti.solvathon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class UserInformation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String cardNo;
    private Integer expiryMonth;
    private Integer expiryYear;
    private String mobileNo;

    private byte[] panToken;
    private byte[] publicToken;
    private byte[] cvvToken;

    @Transient
    private String message;
    
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

    public byte[] getPanToken() {
        return panToken;
    }

    public void setPanToken(byte[] panToken) {
        this.panToken = panToken;
    }

    public byte[] getPublicToken() {
        return publicToken;
    }

    public void setPublicToken(byte[] publicToken) {
        this.publicToken = publicToken;
    }

    public byte[] getCvvToken() {
        return cvvToken;
    }

    public void setCvvToken(byte[] cvvToken) {
        this.cvvToken = cvvToken;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
