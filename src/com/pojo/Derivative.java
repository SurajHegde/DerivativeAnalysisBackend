package com.pojo;
import java.sql.Date;

public class Derivative {
	private String securityName;
	private String type;
	private String position;
	private double strikePrice;
	private String symbol;
	private String isin;
	private Date expiryDate;
	private double underlyingValue;
	private String view;
	private double volatility;
	private double lotSize;
	private int numLots;
	private double premium;
	
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
		this.lotSize = 0;
		this.numLots = 0;
		this.premium = 0;
	}
	public Derivative(String securityName, String type, String position, double strikePrice, String symbol, String isin,
			Date expiryDate, double underlyingValue, String view, double volatility,double lotSize,int numLots,double premium) {
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
	public double getLotSize() {
		return lotSize;
	}
	public void setLotSize(double lotSize) {
		this.lotSize = lotSize;
	}
	public int getNumLots() {
		return numLots;
	}
	public void setNumLots(int numLots) {
		this.numLots = numLots;
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
