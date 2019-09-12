package com.pojo;

public class Strategy {
	String strategyName;
	String views;
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	
	public String toString()
	{
		return strategyName+" "+views;
	}
	
	
}
