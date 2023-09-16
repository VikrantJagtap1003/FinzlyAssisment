package com.finzly.FXTrading.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tradeId;
	private String sender;
	private String reciver;
	private String currencyPair;
	private double amount;
	
	public Trade() {
		super();
	}
	
	public Trade(String sender, String reciver, String currencyPair, double amount) {
		super();
		this.sender = sender;
		this.reciver = reciver;
		this.currencyPair = currencyPair;
		this.amount = amount;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Trade [sender=" + sender + ", reciver=" + reciver + ", currencyPair=" + currencyPair + ", amount="
				+ amount + "]";
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
	
	
	
	

}
