package com.finzly.FXTrading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finzly.FXTrading.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	CurrencyExchangeService ccyService;

	@PostMapping("currency-exchange/{currencyrate}")
	public String addCurrencyExchange(@PathVariable String currencyrate) {
		try {
			ccyService.addCurrencyExchange(currencyrate);
			
			return "Currency Pair Added Succesfully";
		} catch (RuntimeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GetMapping("getAllCurrencyPair")
	public Object getAllCurrencyPair() {
		logger.info("currency pair list is"+ccyService.getAllCurrencyPair());
		return ccyService.getAllCurrencyPair();
	}

	@PutMapping("updateExistingCcy/{ccy}")
	public void updateExistingCcy(@PathVariable String ccy) {
		ccyService.updateCurrencyPairRate(ccy);
	}

}
