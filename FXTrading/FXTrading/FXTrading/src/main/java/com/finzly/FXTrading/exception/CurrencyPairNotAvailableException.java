package com.finzly.FXTrading.exception;

public class CurrencyPairNotAvailableException  extends RuntimeException {
	
	public CurrencyPairNotAvailableException() {
		super("This Currency PAir is Not available");
	}

}
