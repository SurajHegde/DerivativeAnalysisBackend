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
				if(list.get(i).getStrikePrice() < target && 
						list.get(j).getStrikePrice() < target && 
						list.get(i).getStrikePrice() < list.get(j).getStrikePrice()) 
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
	
	public List<StrategyOutput> LongCall(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setType("CE");
					list.get(i).setPosition("LONG");
					semifinal.add(list.get(i));	
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Long Call");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}

		return fi;
	}
	
	public List<StrategyOutput> ShortPut(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setType("PE");
					list.get(i).setPosition("SHORT");
					semifinal.add(list.get(i));	
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Short Put");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}

		return fi;
	}

	public List<StrategyOutput> LongCombo(String symbol, double target, String expiry_date)
	{
		//moderately bullish
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		type = "CE";
		list.addAll(s.getHolding(symbol, type, expiry_date));
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() < target && 
						list.get(j).getStrikePrice() > target) 
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("SHORT");
					list.get(j).setPosition("LONG");
					list.get(i).setType("PE");
					list.get(j).setType("CE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Long Combo");
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
	
	public List<StrategyOutput> ShortCallButterfly(String symbol, double target, String expiry_date)
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
						list.get(i).setPosition("SHORT");
						list.get(j).setPosition("SHORT");
						list.get(k).setPosition("LONG");
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
						so.setStrategyName("Short Call Butterfly");
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

	public List<StrategyOutput> LongCallCondor(String symbol, double target, String expiry_date)
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
					for(int l=0;l<list.size();l++) 
					{
							if(list.get(i).getStrikePrice() < target && 
								list.get(j).getStrikePrice() < target &&
								list.get(k).getStrikePrice() > target &&
								list.get(l).getStrikePrice() > target &&
								list.get(i).getStrikePrice() < list.get(j).getStrikePrice() &&
								list.get(k).getStrikePrice() < list.get(l).getStrikePrice() /* &&
								(target-list.get(j).getStrikePrice() == list.get(i).getStrikePrice()-target) &&
								(list.get(k).getStrikePrice() == target)*/
								// Check for equidistant option strike prices
									)
						{
							List<Holding > semifinal = new ArrayList<>();
							list.get(i).setPosition("LONG");
							list.get(j).setPosition("SHORT");
							list.get(k).setPosition("SHORT");
							list.get(l).setPosition("LONG");
							list.get(i).setType("CE");
							list.get(j).setType("CE");
							list.get(k).setType("CE");
							list.get(l).setType("CE");
							semifinal.add(list.get(i));
							semifinal.add(list.get(j));
							semifinal.add(list.get(k));
							semifinal.add(list.get(l));
							
							List<Pair> coordinates = d.generatePayoff(semifinal);
							double maxProfit = d.calcMaxProfit(coordinates);
							double maxLoss = d.calcMaxLoss(coordinates);
							List<Double> breakevens = d.calcBreakeven(coordinates);
									
							StrategyOutput so = new StrategyOutput();
							so.setStrategyName("Long Call Condor");
							so.setHoldings(semifinal);
							so.setMaxProfit(maxProfit);
							so.setMaxLoss(maxLoss);
							so.setBreakevens(breakevens);
							
							fi.add(so);
					}

					}
				}
					
			}
		}

		return fi;
	}
	
	public List<StrategyOutput> ShortCallCondor(String symbol, double target, String expiry_date)
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
					for(int l=0;l<list.size();l++) 
					{
							if(list.get(i).getStrikePrice() < target && 
								list.get(j).getStrikePrice() < target &&
								list.get(k).getStrikePrice() > target &&
								list.get(l).getStrikePrice() > target &&
								list.get(i).getStrikePrice() < list.get(j).getStrikePrice() &&
								list.get(k).getStrikePrice() < list.get(l).getStrikePrice() /* &&
								(target-list.get(j).getStrikePrice() == list.get(i).getStrikePrice()-target) &&
								(list.get(k).getStrikePrice() == target)*/
								// Check for equidistant option strike prices
									)
						{
							List<Holding > semifinal = new ArrayList<>();
							list.get(i).setPosition("SHORT");
							list.get(j).setPosition("LONG");
							list.get(k).setPosition("LONG");
							list.get(l).setPosition("SHORT");
							list.get(i).setType("CE");
							list.get(j).setType("CE");
							list.get(k).setType("CE");
							list.get(l).setType("CE");
							semifinal.add(list.get(i));
							semifinal.add(list.get(j));
							semifinal.add(list.get(k));
							semifinal.add(list.get(l));
							
							List<Pair> coordinates = d.generatePayoff(semifinal);
							double maxProfit = d.calcMaxProfit(coordinates);
							double maxLoss = d.calcMaxLoss(coordinates);
							List<Double> breakevens = d.calcBreakeven(coordinates);
									
							StrategyOutput so = new StrategyOutput();
							so.setStrategyName("Short Call Condor");
							so.setHoldings(semifinal);
							so.setMaxProfit(maxProfit);
							so.setMaxLoss(maxLoss);
							so.setBreakevens(breakevens);
							
							fi.add(so);
					}

					}
				}
					
			}
		}

		return fi;
	}

	public List<StrategyOutput> LongStraddle(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding (symbol, type, expiry_date);
		
		type = "PE";
		list.addAll(s.getHolding(symbol, type, expiry_date));
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() == list.get(j).getStrikePrice())
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("LONG");
					list.get(i).setType("CE");
					list.get(j).setType("PE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Long Straddle");
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

	
	public List<StrategyOutput> ShortStraddle(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		type = "PE";
		list.addAll(s.getHolding(symbol, type, expiry_date));
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice() == list.get(j).getStrikePrice())
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("SHORT");
					list.get(j).setPosition("SHORT");
					list.get(i).setType("CE");
					list.get(j).setType("PE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Short Straddle");
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
	
	public List<StrategyOutput> LongStrangle(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		type = "PE";
		list.addAll(s.getHolding(symbol, type, expiry_date));
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice()<target  && list.get(j).getStrikePrice()>target)
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("LONG");
					list.get(i).setType("PE");
					list.get(j).setType("CE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Long Strangle");
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

	public List<StrategyOutput> ShortStrangle(String symbol, double target, String expiry_date)
	{
		List<Holding > list = new ArrayList<Holding >();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		type = "PE";
		list.addAll(s.getHolding(symbol, type, expiry_date));
		
		for(int i=0; i<list.size() ; i++)
		{
			for(int j=0;j<list.size();j++)
			{
				if(list.get(i).getStrikePrice()<target  && list.get(j).getStrikePrice()>target)
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("SHORT");
					list.get(j).setPosition("SHORT");
					list.get(i).setType("PE");
					list.get(j).setType("CE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Short Strangle");
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
	
	
	//-------------------------------Bearish------------------------------
	public List<StrategyOutput> BearLongPut(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setType("PE");
					list.get(i).setPosition("LONG");
					semifinal.add(list.get(i));	
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Long Put");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}

		return fi;
	}
	
	public List<StrategyOutput> BearShortCall(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "CE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		
		for(int i=0; i<list.size() ; i++)
		{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setType("CE");
					list.get(i).setPosition("SHORT");
					semifinal.add(list.get(i));	
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Short Call");
					so.setHoldings(semifinal);
					so.setMaxProfit(maxProfit);
					so.setMaxLoss(maxLoss);
					so.setBreakevens(breakevens);
					
					fi.add(so);
				}

		return fi;
	}

	public List<StrategyOutput> BearCallSpread(String symbol, double target, String expiry_date)
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
				if((list.get(i).getStrikePrice() > target && list.get(j).getStrikePrice() < target) || (list.get(i).getStrikePrice()>list.get(j).getStrikePrice()) )
				{
					List<Holding > semifinal = new ArrayList<>();
					list.get(i).setPosition("LONG");
					list.get(j).setPosition("SHORT");
					list.get(i).setType("CE");
					list.get(j).setType("CE");
					semifinal.add(list.get(i));
					semifinal.add(list.get(j));
					
					List<Pair> coordinates = d.generatePayoff(semifinal);
					double maxProfit = d.calcMaxProfit(coordinates);
					double maxLoss = d.calcMaxLoss(coordinates);
					List<Double> breakevens = d.calcBreakeven(coordinates);
							
					StrategyOutput so = new StrategyOutput();
					so.setStrategyName("Bear Call Spread");
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

	
	public List<StrategyOutput> BearPutSpread(String symbol, double target, String expiry_date)
	{
		List<Holding> list = new ArrayList<Holding>();
		
		List<StrategyOutput> fi = new ArrayList<>();
		String type = "PE";
		StrategyDAOImpl s = new StrategyDAOImpl();
		DerivativeLogic d = new DerivativeLogic();
		list = s.getHolding(symbol, type, expiry_date);
		System.out.println(list);
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