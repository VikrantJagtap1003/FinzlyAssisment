package com.finzly.FXTrading.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.finzly.FXTrading.dao.CurrencyExchangeDao;
import com.finzly.FXTrading.entity.CurrencyExchange;


@Component
public class TradeAmountConversion {
	@Autowired
	CurrencyExchangeDao CurrencyExchangeDao;
	public double convertFromTo(String currencyPair,double amount)
	{
		List<CurrencyExchange> currencyPairList=CurrencyExchangeDao.getCurrencyPairRate(currencyPair);
		double conversionRate=currencyPairList.get(0).getCcyrate();
		double convertedAmount=amount*conversionRate;
		return convertedAmount;
		
	}
}
