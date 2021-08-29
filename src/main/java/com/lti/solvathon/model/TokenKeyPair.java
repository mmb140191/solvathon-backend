package com.lti.solvathon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TokenKeyPair {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
	private byte[] publicKey;
	private byte[] privateKey;
	
		public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}
	public byte[] getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}
	
	
}
