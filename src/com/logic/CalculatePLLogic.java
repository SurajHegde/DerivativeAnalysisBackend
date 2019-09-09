package com.logic;


import com.pojo.Holding;
import com.pojo.Output;

public class CalculatePLLogic {
	
	
	
	public Output calculatePL(Holding h)
	{
		double payoff=0d, breakeven=0d, maxProfit=0d, maxLoss=0d;
		Output o = new Output();
		
		switch(h.getType())
		{
		case "CE":
			if(h.getPosition().equals("LONG"))
			{
				payoff = (Math.max((h.getUnderlyingValue() - h.getStrikePrice()),0d) - h.getPremium())*h.getNumLots()*h.getLotSize();	
				breakeven = h.getPremium()+h.getStrikePrice();
				maxProfit = Double.POSITIVE_INFINITY;
				maxLoss = h.getPremium()*h.getNumLots()*h.getLotSize();
			}
			else if(h.getPosition().equals("SHORT"))
			{
				payoff = - ((Math.max((h.getUnderlyingValue() - h.getStrikePrice()),0d) - h.getPremium()))*h.getNumLots()*h.getLotSize();
				breakeven = h.getPremium()+h.getStrikePrice();
				maxProfit = h.getPremium()*h.getNumLots()*h.getLotSize();
				maxLoss = Double.NEGATIVE_INFINITY ;
			}
			break;
			
		case "PE":
			if(h.getPosition().equals("LONG"))
			{
				payoff = (Math.max((h.getStrikePrice()-h.getUnderlyingValue()), 0) - h.getPremium())*h.getNumLots()*h.getLotSize();
				breakeven = h.getStrikePrice() - h.getPremium();
				maxProfit = (h.getStrikePrice() - h.getPremium())*h.getNumLots()*h.getLotSize();
				maxLoss = h.getPremium()*h.getNumLots()*h.getLotSize();
			}
			else if(h.getPosition().equals("SHORT"))
			{
				payoff = - ((Math.max((h.getStrikePrice()-h.getUnderlyingValue()), 0) - h.getPremium()))*h.getNumLots()*h.getLotSize();
				breakeven = h.getStrikePrice() - h.getPremium();
				maxProfit = h.getPremium()*h.getNumLots()*h.getLotSize();
				maxLoss = (h.getStrikePrice() - h.getPremium())*h.getNumLots()*h.getLotSize();
			}
			break;
			
		case "FUT":
			if(h.getPosition().equals("LONG"))
			{
				payoff = (h.getLtp() - h.getAvgPrice())*h.getNumLots()*h.getLotSize(); //h.getAvgPrice() will be stored in holding table
				breakeven = h.getAvgPrice();
				maxProfit = Double.POSITIVE_INFINITY;
				maxLoss = h.getAvgPrice()*h.getNumLots()*h.getLotSize();
			}
			else if(h.getPosition().equals("SHORT"))
			{
				payoff = - ((h.getLtp() - h.getAvgPrice()))*h.getNumLots()*h.getLotSize(); 
				breakeven = h.getAvgPrice();
				maxProfit = h.getAvgPrice()*h.getNumLots()*h.getLotSize();
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
