package com.pojo;
import java.sql.Date;

public class Derivative {
	private String type;
	private String position;
	private double strikePrice;
	private String symbol;
	private String expiryDate;
	private double underlyingValue;
	private double volatility;
	private int lotSize;
	private int numLots;
	private double premium;
	private double ltp;
	
	
	public Derivative() {
		this.type = "";
		this.position = "";
		this.strikePrice = 0;
		this.symbol = "";
		this.expiryDate = null;
		this.underlyingValue = 0;
		this.volatility = 0;
		this.lotSize = 0;
		this.numLots = 0;
		this.premium = 0;
		this.ltp = 0;
	}
	public Derivative(String type, String position, double strikePrice, String symbol,
			String expiryDate, double underlyingValue,double volatility,int lotSize,int numLots,double premium) {
		super();
		this.type = type;
		this.position = position;
		this.strikePrice = strikePrice;
		this.symbol = symbol;
		this.expiryDate = expiryDate;
		this.underlyingValue = underlyingValue;
		this.volatility = volatility;
		this.lotSize = lotSize;
		this.numLots = numLots;
		this.premium = premium;
	}
	
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getUnderlyingValue() {
		return underlyingValue;
	}
	public void setUnderlyingValue(double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}
	public int getLotSize() {
		return lotSize;
	}
	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}
	public int getNumLots() {
		return numLots;
	}
	public void setNumLots(int numLots) {
		this.numLots = numLots;
	}

	public double getVolatility() {
		return volatility;
	}
	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}
	public double getLtp() {
		return ltp;
	}
	public void setLtp(double ltp) {
		this.ltp = ltp;
	}
	
	public String toString()
	{
		return symbol+strikePrice;
	}
	
}
