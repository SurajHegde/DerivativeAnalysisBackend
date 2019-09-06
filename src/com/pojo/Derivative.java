package com.pojo;
import java.sql.Date;

public class Derivative {
	protected String securityName;
	protected String type;
	protected String position;
	protected double strikePrice;
	protected String symbol;
	protected String isin;
	protected Date expiryDate;
	protected double underlyingValue;
	protected String view;
	protected double volatility;
	
	public Derivative() {
		this.securityName = "";
		this.type = "";
		this.position = "";
		this.strikePrice = 0;
		this.symbol = "";
		this.isin = "";
		this.expiryDate = null;
		this.underlyingValue = 0;
		this.view = "";
		this.volatility = 0;
	}
	public Derivative(String securityName, String type, String position, double strikePrice, String symbol, String isin,
			Date expiryDate, double underlyingValue, String view, double volatility) {
		super();
		this.securityName = securityName;
		this.type = type;
		this.position = position;
		this.strikePrice = strikePrice;
		this.symbol = symbol;
		this.isin = isin;
		this.expiryDate = expiryDate;
		this.underlyingValue = underlyingValue;
		this.view = view;
		this.volatility = volatility;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
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
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getUnderlyingValue() {
		return underlyingValue;
	}
	public void setUnderlyingValue(double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public double getVolatility() {
		return volatility;
	}
	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}
	
	
	
}
