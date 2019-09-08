package com.logic;

import com.pojo.Output;

public class CalculatePLLogic {
	
	public Output calculatePL(double underlyingPrice, double strikePrice, double premium, String position, String type, int lotSize, int numLot, double LTP, double avgPrice)
	{
		Output o = new Output();
		double payoff=0d, breakeven=0d, maxProfit=0d, maxLoss=0d;
		
		switch(type)
		{
		case "CE":
			if(position.equals("LONG"))
			{
				payoff = (Math.max((underlyingPrice - strikePrice),0d) - premium)*numLot*lotSize;	
				breakeven = premium+strikePrice;
				maxProfit = Double.POSITIVE_INFINITY;
				maxLoss = premium*numLot*lotSize;
			}
			else if(position.equals("SHORT"))
			{
				payoff = - ((Math.max((underlyingPrice - strikePrice),0d) - premium))*numLot*lotSize;
				breakeven = premium+strikePrice;
				maxProfit = premium*numLot*lotSize;
				maxLoss = Double.NEGATIVE_INFINITY ;
			}
			break;
			
		case "PE":
			if(position.equals("LONG"))
			{
				payoff = (Math.max((strikePrice-underlyingPrice), 0) - premium)*numLot*lotSize;
				breakeven = strikePrice - premium;
				maxProfit = (strikePrice - premium)*numLot*lotSize;
				maxLoss = premium*numLot*lotSize;
			}
			else if(position.equals("SHORT"))
			{
				payoff = - ((Math.max((strikePrice-underlyingPrice), 0) - premium))*numLot*lotSize;
				breakeven = strikePrice - premium;
				maxProfit = premium*numLot*lotSize;
				maxLoss = (strikePrice - premium)*numLot*lotSize;
			}
			break;
			
		case "FUT":
			if(position.equals("LONG"))
			{
				payoff = (LTP - avgPrice)*numLot*lotSize; //avgPrice will be stored in holding table
				breakeven = avgPrice;
				maxProfit = Double.POSITIVE_INFINITY;
				maxLoss = avgPrice*numLot*lotSize;
			}
			else if(position.equals("SHORT"))
			{
				payoff = - ((LTP - avgPrice))*numLot*lotSize; 
				breakeven = avgPrice;
				maxProfit = avgPrice*numLot*lotSize;
				maxLoss = Double.NEGATIVE_INFINITY;
			}
			break;
		}
			
		o.setPayoff(payoff);
		o.setBreakeven(breakeven);
		o.setMaxProfit(maxProfit);
		o.setMaxLoss(maxLoss);
		return o;
	}
	
}
