package com.FXTrading.services;

import java.util.List;

import com.FXTrading.model.FXTradingEntity;

public interface FXTradingFormat {
	
	public Object doTrade(FXTradingEntity fxTradingEntity);
	public List<FXTradingEntity> getTradeList();

}
