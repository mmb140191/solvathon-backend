package com.lti.solvathon.model;

public class PaymentInformation {

	private byte[] panToken;
    private byte[] publicToken;
    private byte[] cvvToken;
    private double transactionAmount;
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
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
    
    
}
