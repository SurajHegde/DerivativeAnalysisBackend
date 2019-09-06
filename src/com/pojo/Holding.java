package com.pojo;

public class Holding extends Derivative {
	private double avgPrice;
	private double netValue;
	private double spotPrice;
	private int quantity;
	
	public Holding() {
		super();
		this.avgPrice = 0;
		this.netValue = 0;
		this.spotPrice = 0;
		this.quantity = 0;
	}
	
	public Holding(double avgPrice, double netValue, double spotPrice, int quantity) {
		super();
		this.avgPrice = avgPrice;
		this.netValue = netValue;
		this.spotPrice = spotPrice;
		this.quantity = quantity;
	}
	
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public double getNetValue() {
		return netValue;
	}
	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}
	public double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
}
