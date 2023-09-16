package com.finzly.FXTrading.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrencyExchange {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ccyId;
	private String currencyPair;
	private double ccyrate;
	
	
	public CurrencyExchange() {
		super();
	}


	public CurrencyExchange(int ccyId, String currencyPair, double ccyrate) {
		super();
		this.ccyId = ccyId;
		this.currencyPair = currencyPair;
		this.ccyrate = ccyrate;
	}


	public int getCcyId() {
		return ccyId;
	}


	public void setCcyId(int ccyId) {
		this.ccyId=ccyId;
	}


	public String getCurrencyPair() {
		return currencyPair;
	}


	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}


	public double getCcyrate() {
		return ccyrate;
	}


	public void setCcyrate(double ccyrate) {
		this.ccyrate = ccyrate;
	}


	@Override
	public String toString() {
		return "CurrencyExchange [ccyId=" + ccyId + ", currencyPair=" + currencyPair + ", ccyrate=" + ccyrate + "]";
	}
	
	

}
