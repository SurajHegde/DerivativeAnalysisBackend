package com.pojo;

import java.util.List;

public class StrategyOutput {
	String strategyName;
	List<Holding> holdings;
	double maxProfit;
	double maxLoss;
	List<Double> breakevens;
	
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public List<Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}
	public double getMaxProfit() {
		return maxProfit;
	}
	public void setMaxProfit(double maxProfit) {
		this.maxProfit = maxProfit;
	}
	public double getMaxLoss() {
		return maxLoss;
	}
	public void setMaxLoss(double maxLoss) {
		this.maxLoss = maxLoss;
	}
	public List<Double> getBreakevens() {
		return breakevens;
	}
	public void setBreakevens(List<Double> breakevens) {
		this.breakevens = breakevens;
	}

	
	public String toString()
	{
		return strategyName+" "+holdings+" "+maxProfit+" "+maxLoss+" "+breakevens;
	}
}
