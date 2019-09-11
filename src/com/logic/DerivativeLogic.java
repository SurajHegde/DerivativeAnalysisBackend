package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.pojo.Holding;

public class DerivativeLogic {

	public double calcMaxProfit(List<Pair> coordinateList) {
		return Collections.max(coordinateList,Comparator.comparing(coord -> coord.getY())).getY();
	}
	public double calcMaxLoss(List<Pair> coordinateList) {
		return Collections.min(coordinateList,Comparator.comparing(coord -> coord.getY())).getY();
	}
	public List<Double> calcBreakeven(List<Pair> coordinateList) {
		List<Double> breakEvenPoints = new ArrayList<>();
		for (int i=0;i<(coordinateList.size()-1);i++) {
			if (coordinateList.get(i).getY()*coordinateList.get(i+1).getY() < 0) {	
				double distance = (coordinateList.get(i+1).getX()-coordinateList.get(i).getX())*Math.abs(coordinateList.get(i).getY()/(coordinateList.get(i+1).getY()-coordinateList.get(i).getY()));
				breakEvenPoints.add((coordinateList.get(i).getX()+distance));
			}
		}
		return breakEvenPoints;
	}
	public double calcFairPrice() {
		return 0;
	}
	public List<Pair> generatePayoff(List<Holding> holdingList) {
		// TODO Auto-generated method stub
		List<Pair> coordinateList = new ArrayList<>();

		Collections.sort(holdingList,(h1,h2) -> ((int) (h1.getStrikePrice() - h2.getStrikePrice())));
		
		CalculatePLLogic payoffCalc = new CalculatePLLogic();
		double netPL = 0.0;
		
		for (Holding holding:holdingList) {
			netPL+= payoffCalc.calculatePL(new Holding(holding.getType(),holding.getPosition(),holding.getStrikePrice(),holding.getSymbol(),holding.getExpiryDate(),0,holding.getVolatility(),holding.getLotSize(),holding.getNumLots(),holding.getPremium(),0,holding.getSpotPrice())).getPayoff(); 
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
				netPL+= payoffCalc.calculatePL(new Holding(holding.getType(),holding.getPosition(),holding.getStrikePrice(),holding.getSymbol(),holding.getExpiryDate(),holdingList.get(i).getStrikePrice(),holding.getVolatility(),holding.getLotSize(),holding.getNumLots(),holding.getPremium(),holdingList.get(i).getStrikePrice(),holding.getSpotPrice())).getPayoff(); 
			}
			coordinateList.add(new Pair(holdingList.get(i).getStrikePrice(),netPL));	

		}
		
		netPL = 0.0;
		
		for (Holding holding:holdingList) {
			netPL+= payoffCalc.calculatePL(new Holding(holding.getType(),holding.getPosition(),holding.getStrikePrice(),holding.getSymbol(),holding.getExpiryDate(),999999d,holding.getVolatility(),holding.getLotSize(),holding.getNumLots(),holding.getPremium(),999999d,holding.getSpotPrice())).getPayoff(); 
		}
		
		coordinateList.add(new Pair(999999d, netPL));

		return coordinateList;
	}	
//	public static void main(String[] args) {
//	Scanner scanner = new Scanner(System.in);
//	String nextOption = "y";
//	List<Holding> holdingList = new ArrayList<>();
//	while (nextOption.toUpperCase().equals("Y")) {
//		Holding h1 = new Holding();
//		System.out.print("Enter derivative type : ");
//		String optionType = scanner.next();
//		h1.setType(optionType);
//		System.out.print("Enter derivative position : ");
//		String optionPosition = scanner.next();
//		h1.setPosition(optionPosition);
//		if (optionType.equals("PE")||optionType.equals("CE")) {
//			System.out.print("Enter option premium : ");
//			double optionPremium = scanner.nextDouble();
//			h1.setPremium(optionPremium);
//			System.out.print("Enter option strike price : ");
//			double optionStrikePrice = scanner.nextDouble();
//			h1.setStrikePrice(optionStrikePrice);
//			System.out.println();
//			h1.setLotSize(5);
//			h1.setNumLots(1);
//			holdingList.add(h1);
//			}
//		else {
//			h1.setStrikePrice(0);
//			System.out.print("Enter price of future : ");
//			double futurePrice = scanner.nextDouble();
//			h1.setAvgPrice(futurePrice);
//			System.out.println();
//			h1.setLotSize(5);
//			h1.setNumLots(1);
//			holdingList.add(h1);				
//		}
//		System.out.print("Continue? : ");
//		nextOption = scanner.next();
//		System.out.println("\n");
//	}
//	
//	DerivativeLogic dltest = new DerivativeLogic();
//	
//	List<Pair> coordinateList = dltest.generatePayoff(holdingList);
//	List<Double> breakEvenList = dltest.calcBreakeven(coordinateList);
//	double maxProfit = dltest.calcMaxProfit(coordinateList);
//	double maxLoss = dltest.calcMaxLoss(coordinateList);
//	
//	System.out.print("Breakeven points = ");
//	for (Double breakEven:breakEvenList) {
//		System.out.print(breakEven+" ");
//	}
//	System.out.println("\n");
//	System.out.println("Max profit = "+maxProfit);
//	System.out.println("Max loss = "+maxLoss);
//	}
}

