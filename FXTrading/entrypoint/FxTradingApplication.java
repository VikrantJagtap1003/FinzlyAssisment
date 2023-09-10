package com.FXTrading.entrypoint;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.FXTrading.controller","com.FXTrading.services","com.FXTrading.utility"})
public class FxTradingApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FxTradingApplication.class, args);
		
		System.out.println("Application Started");
	}

}
