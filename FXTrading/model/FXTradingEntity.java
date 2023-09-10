package com.FXTrading.model;


public class FXTradingEntity{
	

	
	private String usd_Amount;
	
	private int customerID;
	
	private String customerName;
	

	private String currencyPair;
	
	
	public String getIndianAmount() {
		return indianAmount;
	}

	public void setIndianAmount(String indianAmount) {
		this.indianAmount = indianAmount;
	}

	private String indianAmount;
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	
	public FXTradingEntity() {
		super();
	}

	public FXTradingEntity(String usd_Amount, int customerID, String customerName, String currencyPair) {
		super();
		this.usd_Amount = usd_Amount;
		this.customerID = customerID;
		this.customerName = customerName;
		this.currencyPair = currencyPair;
	}

	@Override
	public String toString() {
		return "FXTradingEntity [usd_Amount=" + usd_Amount + ", customerID=" + customerID + ", customerName="
				+ customerName + ", currencyPair=" + currencyPair + ", indianAmount=" + indianAmount + "]";
	}

	public String getUsd_Amount() {
		return usd_Amount;
	}

	public void setUsd_Amount(String usd_Amount) {
		this.usd_Amount = usd_Amount;
	}

	

	
	

	

	
	
	
	
	
}