package com.FXTrading.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private List<FXTradingEntity> bookedTradesEntities=new LinkedList<>();
	

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
			
			
	          
			this.bookedTradesEntities.add(fxTradingEntity);
			return fxTradingEntity;
		}
		else {
			return errorsByUser;
		}
		
	}
	
//	private String formatingUSDAmount(double amount)
//	{
//		DecimalFormat df = new DecimalFormat("#");
//		df.setMaximumFractionDigits(2);
//        String str=df.format(amount);
//        DecimalFormat df1 = new DecimalFormat("##,###.00");
//        String formatedAmount=df1.format(Double.parseDouble(str));
//		return formatedAmount;
//	}
	
	
	@Override
	public List<FXTradingEntity> getTradeList()
	{
		return this.bookedTradesEntities;
	}
	

//	private String converting_USD_To_INR(double amount)
//    {
//
//		double convertedAmount=amount*66;
//
//		DecimalFormat df = new DecimalFormat("#");
//		df.setMaximumFractionDigits(2);
//        String str=df.format(convertedAmount)+".00";
//        
//        
//        //inserting  ',' to get Indian format
//        
//        String formatedAmount="";
//        boolean setter=true;
//        boolean thousandSetter=false;
//        boolean otherSetter=false;
//        int counter=0;
//        for(int i=str.length()-1;i>=0;i--)
//        {
//            if(str.charAt(i)=='.')
//            {
//                formatedAmount=str.charAt(i)+formatedAmount;
//                setter=false;
//                thousandSetter=true;
//                counter=0;
//            }
//            else if(setter)
//            {
//                formatedAmount=str.charAt(i)+formatedAmount;
//
//            }
//            else if(thousandSetter && counter==4)
//            {
//                formatedAmount=str.charAt(i)+","+formatedAmount;
//                thousandSetter=false;
//                otherSetter=true;
//                counter=0;
//
//            }
//            else if(otherSetter && counter==2)
//            {
//                formatedAmount=str.charAt(i)+","+formatedAmount;
//                counter=0;
//            }
//            else{
//                formatedAmount=str.charAt(i)+formatedAmount;
//            }
//            counter++;
//        }
//        return formatedAmount+" INR";
//
//    }

	
	
}
