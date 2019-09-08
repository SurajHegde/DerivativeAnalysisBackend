package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pojo.Holding;

public class DerivativeLogic {

	private double calcMaxProfit() {
		return 0;
	}
	private double calcMaxLoss() {
		return 0;
	}
	private List<Double> calcBreakeven(List<Pair> coordinateList) {
		List<Double> breakEvenPoints = new ArrayList<>();
		for (int i=0;i<(coordinateList.size()-1);i++) {
			double distance = (coordinateList.get(i+1).getX()-coordinateList.get(i).getX())*Math.abs(coordinateList.get(i).getY()/(coordinateList.get(i+1).getY()-coordinateList.get(i).getY()));
			breakEvenPoints.add((coordinateList.get(i).getX()+distance));
		}
		return breakEvenPoints;
	}
	private double calcFairPrice() {
		return 0;
	}
	public List<Pair> generatePayoff(List<Holding> holdingList) {
		// TODO Auto-generated method stub
		List<Pair> coordinateList = new ArrayList<>();

		Collections.sort(holdingList,(h1,h2) -> ((int) (h1.getStrikePrice() - h2.getStrikePrice())));
		
		CalculatePLLogic payoffCalc = new CalculatePLLogic();
		double netPL = 0.0;
		
		for (Holding holding:holdingList) {
			netPL+= payoffCalc.calculatePL(0, holding.getStrikePrice(), holding.getPremium(), holding.getPosition(), holding.getType(), holding.getLotSize(), holding.getNumLots(), 0, holding.getAvgPrice()).getPayoff(); 
		}
		
		coordinateList.add(new Pair(0.0, netPL));
		
		for (int i=0;i<holdingList.size();i++) {
			if (i<holdingList.size()-1) {	
				if (holdingList.get(i).getStrikePrice() == holdingList.get(i+1).getStrikePrice()) {
					continue;
				}
			}
			netPL = 0.0;
			for (Holding holding:holdingList) {
				netPL+= payoffCalc.calculatePL(holdingList.get(i).getStrikePrice(), holding.getStrikePrice(), holding.getPremium(), holding.getPosition(), holding.getType(), holding.getLotSize(), holding.getNumLots(), holdingList.get(i).getStrikePrice(), holding.getAvgPrice()).getPayoff(); 
			}
			coordinateList.add(new Pair(holdingList.get(i).getStrikePrice(),netPL));	

		}
		
		netPL = 0.0;
		
		for (Holding holding:holdingList) {
			netPL+= payoffCalc.calculatePL(999999d, holding.getStrikePrice(), holding.getPremium(), holding.getPosition(), holding.getType(), holding.getLotSize(), holding.getNumLots(), 999999d, holding.getAvgPrice()).getPayoff(); 
		}
		
		coordinateList.add(new Pair(999999d, netPL));

		return coordinateList;
	}	

}

