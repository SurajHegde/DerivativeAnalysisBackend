package com.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.logic.CalculatePLLogic;

import com.logic.Pair;
import com.pojo.Holding;

public class DerivativeDAOImpl implements DerivativeDAO {

	@Override
	public List<Pair> generatePayoff(List<Holding> holdingList) {
		// TODO Auto-generated method stub
		List<Pair> coordinateList = new ArrayList<>();
		
		Collections.sort(holdingList,(h1,h2) -> ((int) (h1.getStrikePrice() - h2.getStrikePrice())));
		
		for (int i=0;i<holdingList.size();i++) {
			if (i<holdingList.size()-1) {	
				if (holdingList.get(i).getStrikePrice() == holdingList.get(i+1).getStrikePrice()) {
					continue;
				}
			}
			double netPL = 0.0;
			for (Holding holding:holdingList) {
				CalculatePLLogic payoffCalc = new CalculatePLLogic();
				netPL+= payoffCalc.calculatePL(holdingList.get(i).getStrikePrice(), holding.getStrikePrice(), holding.getPremium(), holding.getPosition(), holding.getType(), holding.getLotSize(), holding.getNumLots(), holdingList.get(i).getStrikePrice(), holding.getAvgPrice()).getPayoff(); 
			}
			coordinateList.add(new Pair(holdingList.get(i).getStrikePrice(),netPL));	
			
		}
		
		return coordinateList;
	}	
}
