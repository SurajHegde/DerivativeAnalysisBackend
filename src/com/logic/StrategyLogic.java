package com.logic;

import java.util.ArrayList;
import java.util.List;

import com.dao.StrategyDAOImpl;
import com.pojo.Derivative;
import com.pojo.Strategy;

public class StrategyLogic {
	
	public List<Strategy> getStrategy(String symbol,String views,String targetDate,double target)
	{
		StrategyDAOImpl strategyDAOImpl = new StrategyDAOImpl();
		List<Strategy> strategies = strategyDAOImpl.getStrategies(views);
		System.out.println(strategies);
		return strategies;
	}
	
	public List<List<Derivative>> BullCallSpread(String symbol, double target, String expiry_date)
	{
		List<Derivative> list = new ArrayList<Derivative>();
		
		List<List<Derivative>> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		list = s.getDerivative(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target)
				{
					List<Derivative> semifinal = new ArrayList<>();
					list.get(i).setPosition("SHORT");
					list.get(j).setPosition("LONG");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					fi.add(semifinal);
				}
					
			}
		}

		return fi;
	}
	
	public List<List<Derivative>> LongCallButterfly(String symbol, double target, String expiry_date)
	{
		List<Derivative> list = new ArrayList<Derivative>();
		
		List<List<Derivative>> fi = new ArrayList<>();
		StrategyDAOImpl s = new StrategyDAOImpl();
		String type = "CE";
		list = s.getDerivative(symbol, type, expiry_date);
		
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
						List<Derivative> semifinal = new ArrayList<>();
						list.get(i).setPosition("LONG");
						list.get(j).setPosition("LONG");
						list.get(k).setPosition("SHORT");
						semifinal.add(list.get(i));
						semifinal.add(list.get(j));
						semifinal.add(list.get(k));
						semifinal.add(list.get(k));
						fi.add(semifinal);
					}
				}
					
			}
		}

		return fi;
	}
	
	public List<List<Derivative>> BearPutSpread(String symbol, double target, String expiry_date)
	{
		List<Derivative> list = new ArrayList<Derivative>();
		
		List<List<Derivative>> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		list = s.getDerivative(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target)
				{
					List<Derivative> semifinal = new ArrayList<>();
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("SHORT");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					fi.add(semifinal);
				}
					
			}
		}

		return fi;
	}
	
}
