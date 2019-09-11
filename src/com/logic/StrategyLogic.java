package com.logic;

import java.util.ArrayList;
import java.util.List;

import com.dao.StrategyDAOImpl;
import com.pojo.Holding ;
import com.pojo.Strategy;
import com.pojo.StrategyOutput;

public class StrategyLogic {
	
	public List<Strategy> getStrategy(String symbol,String views,String targetDate,double target)
	{
		StrategyDAOImpl strategyDAOImpl = new StrategyDAOImpl();
		List<Strategy> strategies = strategyDAOImpl.getStrategies(views);
		System.out.println(strategies);
		return strategies;
	}
	// ----------------------------------Bullish Strategies-------------------
	
	
	public List<StrategyOutput> BullCallSpread(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding (symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target)
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("SHORT");
					list.get(j).setPosition("LONG");
					list.get(i).setType("CE");
					list.get(j).setType("CE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Bull Call Spread");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}
					
			}
		}

		return fi;
	}
	
	public List<StrategyOutput> BullPutSpread(String symbol, double target, String expiry_date)
	{
		//moderately bullish
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target)
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("SHORT");
					list.get(i).setType("PE");
					list.get(j).setType("PE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Bull Put Spread");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}
					
			}
		}

		return fi;
	}
	
	
	//------------------------------Neutral-----------------------
	
	public List<StrategyOutput> LongCallButterfly(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		String type = "CE";
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				for(int k=0;k<list.size();k++)
				{
					if(list.get(i).getStrikePrice() > target && 
							list.get(j).getStrikePrice() < target && 
							(target-list.get(j).getStrikePrice() == list.get(i).getStrikePrice()-target) &&
							(list.get(k).getStrikePrice() == target))
					{
						List<Holding > semifinal = new ArrayList<>();
						list.get(i).setPosition("LONG");
						list.get(j).setPosition("LONG");
						list.get(k).setPosition("SHORT");
						list.get(i).setType("CE");
						list.get(j).setType("CE");
						list.get(k).setType("CE");
						semifinal.add(list.get(i));
						semifinal.add(list.get(j));
						semifinal.add(list.get(k));
						semifinal.add(list.get(k));
						
						List<Pair> coordinates = d.generatePayoff(semifinal);
						double maxProfit = d.calcMaxProfit(coordinates);
						double maxLoss = d.calcMaxLoss(coordinates);
						List<Double> breakevens = d.calcBreakeven(coordinates);
								
						StrategyOutput so = new StrategyOutput();
						so.setStrategyName("Long Call Butterfly");
						so.setHoldings(semifinal);
						so.setMaxProfit(maxProfit);
						so.setMaxLoss(maxLoss);
						so.setBreakevens(breakevens);
						
						fi.add(so);
					}
				}
					
			}
		}

		return fi;
	}
	
	
	//-------------------------------Bearish------------------------------
	public List<StrategyOutput> BearPutSpread(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target)
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setType("PE");
					list.get(j).setType("PE");
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("SHORT");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Bear Put Spread");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}
					
			}
		}

		return fi;
	}
	

}
