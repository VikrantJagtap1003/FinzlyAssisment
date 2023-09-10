package com.FXTrading.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.FXTrading.model.FXTradingEntity;
import com.FXTrading.utility.AmountFormater;

@Component
public class FXTradingFormatImpl  implements FXTradingFormat{
	@Autowired
	AmountFormater amountFormater;
	private static int tradeNo=0;
	private List<FXTradingEntity> bookedTrades=new LinkedList<>();
	

	@Override
	public Object doTrade(FXTradingEntity fxTradingEntity){
		double validAmount=0;
		Map<String, String> errorsByUser=new TreeMap<>();
		
		if(fxTradingEntity.getCustomerName().isBlank())
		{
			errorsByUser.put("Customer-Name", "Customer name cannot be empty");
		}
		if(fxTradingEntity.getUsd_Amount().isBlank())
		{
			errorsByUser.put("Usd Amount", "Usd Amount cannot be empty");
		}
		else{
			try {
				 validAmount=Double.parseDouble(fxTradingEntity.getUsd_Amount());
				 System.out.println(validAmount);
				if(validAmount<0.0)
				{
					errorsByUser.put("Usd Amount", "Usd Amount cannot be Negative");
				}
			} catch (Exception e) {
				errorsByUser.put("Usd Amount", "Usd Amount must be integer value");
			}
		}
		if(!fxTradingEntity.getCurrencyPair().equals("USDINR"))
		{
			errorsByUser.put("Currency-Pair", "Currency pair is not USDINR");
		}
		
		
		if(errorsByUser.isEmpty())
		{
			String indianAmount=amountFormater.converting_USD_To_INR(validAmount);
			fxTradingEntity.setIndianAmount(indianAmount);
			String formatedUsdAmount=amountFormater.formatingUSDAmount(validAmount);
			fxTradingEntity.setUsd_Amount(formatedUsdAmount);

			FXTradingFormatImpl.tradeNo=FXTradingFormatImpl.tradeNo+1;
			fxTradingEntity.setTradeId(tradeNo);
			
			this.bookedTrades.add(fxTradingEntity);
			return fxTradingEntity;
		}
		else {
			return errorsByUser;
		}
		
	}

	
	@Override
	public List<FXTradingEntity> getTradeList()
	{
		return this.bookedTrades;
	}
	


	
	
}
