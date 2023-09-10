package com.FXTrading.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.FXTrading.model.FXTradingEntity;
import com.FXTrading.services.FXTradingFormatImpl;

@RestController
public class FXTradingRequests {

	@Autowired
	private FXTradingFormatImpl fxTradingFormatImpl;

	@PostMapping(value = "/do-trade")
	public ResponseEntity<?> doTrading(@RequestBody FXTradingEntity fxTradingEntity) {
		
		if (fxTradingEntity == null) {
			return new ResponseEntity<>("Trade not Booked", HttpStatus.NOT_ACCEPTABLE);
		}
		
			Object doTradeReturnedObject = fxTradingFormatImpl.doTrade(fxTradingEntity);
			if (doTradeReturnedObject instanceof Map) {
				return new ResponseEntity<>(doTradeReturnedObject, HttpStatus.NOT_ACCEPTABLE);
			} 
			else {
			   return new ResponseEntity<>("Trade  Booked", HttpStatus.OK);
			}

		
	}

	@GetMapping("/get-all-trades")
	public ResponseEntity<?> getAllTrades() {
		List<FXTradingEntity> tradeList = this.fxTradingFormatImpl.getTradeList();
		if (tradeList.isEmpty()) {
			 return new ResponseEntity<>("Trade list is empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(tradeList, HttpStatus.OK);
		}
	}

}
