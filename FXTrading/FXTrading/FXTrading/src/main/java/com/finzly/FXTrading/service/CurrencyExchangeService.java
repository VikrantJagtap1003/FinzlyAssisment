package com.finzly.FXTrading.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finzly.FXTrading.dao.CurrencyExchangeDao;
import com.finzly.FXTrading.entity.CurrencyExchange;
import com.finzly.FXTrading.entity.Trade;
import com.finzly.FXTrading.exception.CustomException;
import com.finzly.FXTrading.utility.TradeAmountConversion;

@Service
public class CurrencyExchangeService {
	@Autowired
	CurrencyExchangeDao ccyDao;
	@Autowired
	TradeAmountConversion tradeAmountConversion;
	
	public void addCurrencyExchange(String currencyRate) {
		
			String[] splitCurrencyPair=currencyRate.split("=");
			Double convertedRate;
			if(splitCurrencyPair.length==2)
			{
				String currencyPair=splitCurrencyPair[0];
				String rate=splitCurrencyPair[1];
				
				Object check=ccyDao.checkcurrencyPair(currencyPair);
				if(check==null)
				{
					CurrencyExchange currencyExchangerObj=new CurrencyExchange();
					try {
						 convertedRate=Double.parseDouble(rate);
					}
					catch(NumberFormatException e) {
						throw e;
					}
					if(convertedRate<0) {
						CustomException.NegativeAmountException();
					}
					currencyExchangerObj.setCcyrate(convertedRate);
					currencyExchangerObj.setCurrencyPair(currencyPair);
					String status=ccyDao.addCurrencyExchange(currencyExchangerObj);
					
				}
				else {
					CustomException.currencyPairAlreadyExists();
				}
			}
			else {
				CustomException.improperCurrencyPairFormatException();
			}				
	}

	public Object getAllCurrencyPair() {
		List<CurrencyExchange>currencyExchanges=ccyDao.getAllCurrencyPair();
		if(currencyExchanges.isEmpty()) {
			return "Your CurrencyPair List is Empty";
		}
		else {
			return currencyExchanges;
		}
		
	}

	public void updateCurrencyPairRate(String ccyRate) {
		
		String[] splitCurrencyPair=ccyRate.split("=");
		Double convertedRate;
		if(splitCurrencyPair.length==2)
		{
			String currencyPair=splitCurrencyPair[0];
			String rate=splitCurrencyPair[1];
			
			Object check=ccyDao.checkcurrencyPair(currencyPair);
			if(check!=null)
			{
				CurrencyExchange currencyExchangerObj=new CurrencyExchange();
				try {
					 convertedRate=Double.parseDouble(rate);
				}
				catch(NumberFormatException e) {
					throw e;
				}
				if(convertedRate<0) {
					CustomException.NegativeAmountException();
				}
				currencyExchangerObj.setCcyrate(convertedRate);
				currencyExchangerObj.setCurrencyPair(currencyPair);
				ccyDao.updateCurrencyPair(currencyExchangerObj);
				
			}
			else {
				CustomException.CurrencyPairNotAvailableException();
			}
		}
		else {
			CustomException.improperCurrencyPairFormatException();
		}
		
	}


}
