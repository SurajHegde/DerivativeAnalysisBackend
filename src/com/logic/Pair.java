package com.logic;

public class Pair {
	private double x;
	private double y;
	
	public Pair() {
		this.x = 0;
		this.y = 0;
	}
	public Pair(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String toString(){
		return this.x+" "+this.y;
	}
}
