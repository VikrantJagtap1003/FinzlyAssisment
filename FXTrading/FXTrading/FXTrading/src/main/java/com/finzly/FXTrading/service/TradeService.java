package com.finzly.FXTrading.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.FXTrading.dao.CurrencyExchangeDao;
import com.finzly.FXTrading.dao.TradeDao;
import com.finzly.FXTrading.entity.Trade;
import com.finzly.FXTrading.exception.CustomException;
import com.finzly.FXTrading.utility.TradeAmountConversion;

@Service
public class TradeService {
	
	@Autowired
	CurrencyExchangeDao currencyExchangeDao;
	@Autowired
	TradeAmountConversion tradeAmountConversion;
	@Autowired
	TradeDao tradeDao;
	
	
	public void doTrade(Trade trade) throws RuntimeException{
		Object checkCurrencyReturnObj=currencyExchangeDao.checkcurrencyPair(trade.getCurrencyPair());
		if(checkCurrencyReturnObj==null) {
			 CustomException.CurrencyPairNotAvailableException();
		}
		else if(trade.getAmount()<0) {
			CustomException.NegativeAmountException();
		}
		
		double convertedamount=tradeAmountConversion.convertFromTo(trade.getCurrencyPair(),trade.getAmount());
		trade.setAmount(convertedamount);
		tradeDao.addTrade(trade);	
	}


	public Object getBookedTradeList() {
		List <Trade> tradelist=tradeDao.getBookedTradeList();
		if(tradelist.isEmpty()) {
			return "your trade list is Empty";
		}
		else {
			return tradelist;
		}
		
	}
	

}
