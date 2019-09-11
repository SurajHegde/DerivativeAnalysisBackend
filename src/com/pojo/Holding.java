package com.pojo;

import java.sql.Date;

public class Holding extends Derivative {
	private double avgPrice;
	private double spotPrice;
	
	public Holding() {
		super();
		this.avgPrice = 0;
		this.spotPrice = 0;
	}
	
	public Holding(String type, String position, double strikePrice, String symbol,
			String expiryDate, double underlyingValue, double volatility,int lotSize,int numLots,double premium,double avgPrice, double spotPrice) {
		super(type, position, strikePrice, symbol, expiryDate, underlyingValue,volatility, lotSize, numLots, premium);
		this.avgPrice = avgPrice;
		this.spotPrice = spotPrice;
	}
	
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(double spotPrice) {
		this.spotPrice = spotPrice;
	}
	
	
	
	
	
	
}
