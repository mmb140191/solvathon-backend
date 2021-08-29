package com.lti.solvathon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TokenValt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String cardNo;
    private byte[] cardToken;
    private byte[] privateKey;

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

	public byte[] getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

	public byte[] getCardToken() {
		return cardToken;
	}

	public void setCardToken(byte[] cardToken) {
		this.cardToken = cardToken;
	}
    
	@Override
	public boolean equals(Object obj) {
		return this.cardNo.equals(((TokenValt)obj).cardNo);
	}
	
 }
