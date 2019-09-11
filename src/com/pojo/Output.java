package com.pojo;

public class Output {
	private double breakeven, payoff, maxProfit, maxLoss;

	public double getBreakeven() {
		return breakeven;
	}
	public void setBreakeven(double breakeven) {
		this.breakeven = breakeven;
	}
	public double getPayoff() {
		return payoff;
	}
	public void setPayoff(double payoff) {
		this.payoff = payoff;
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

	public String toString()
	{
		return "\nBreakeven: "+breakeven+
				"\nPayoff: "+payoff+
				"\nMax Profit: "+maxProfit+
				"\nMax Loss: "+maxLoss;
	}

}
