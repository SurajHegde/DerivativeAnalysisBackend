package com.controller.logic;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.dao.DerivativeDAOImpl;
import com.dao.UserDAOImpl;
import com.logic.CalculatePLLogic;
import com.logic.DerivativeLogic;
import com.logic.Pair;
import com.pojo.Holding;

@Path("/generatepayoff")
public class GeneratePayoff {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject generatePayoff(List<JSONObject> incomingData) {
		
		String message;
		String url = "/app/analysis";
		List<Holding> holdingList = new ArrayList<>();;
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		for (JSONObject jsonObject:incomingData) {
			Holding holding = new Holding();
			
			if (((String)jsonObject.get("type")).equals("FUT")) {
				holding.setType((String)jsonObject.get("type"));
				holding.setPosition((String)jsonObject.get("position"));
				holding.setNumLots(Integer.parseInt((String)jsonObject.get("quantity")));
				holding.setAvgPrice(Double.parseDouble((String)jsonObject.get("price")));
				holding.setStrikePrice(0);
				String symbol = ((String)jsonObject.get("symbol"));
				if(jsonObject.get("lotSize").equals("")) {
					int lotSize = dl.getLotSize(symbol);
					holding.setLotSize(lotSize);
				}
				else {
					holding.setLotSize(Integer.parseInt((String)jsonObject.get("lotSize")));
				}
					
			}
			else {
				holding.setType((String)jsonObject.get("type"));
				holding.setPremium(Double.parseDouble((String)jsonObject.get("price")));
				holding.setPosition((String)jsonObject.get("position"));
				holding.setNumLots(Integer.parseInt((String)jsonObject.get("quantity")));
				holding.setStrikePrice(Double.parseDouble((String)jsonObject.get("strikePrice")));
				String symbol = ((String)jsonObject.get("symbol"));
				if(jsonObject.get("lotSize") == null) {
					int lotSize = dl.getLotSize(symbol);
					holding.setLotSize(lotSize);
				} else {
					
					holding.setLotSize(Integer.parseInt((String)jsonObject.get("lotSize")));
				}
			}
			
			holdingList.add(holding);	
		}
		
		JSONObject response = new JSONObject();
		
		DerivativeLogic dlogic = new DerivativeLogic();
		List<Pair> coordinateList = dlogic.generatePayoff(holdingList);
		
		List<Double> breakEvenPoints = dlogic.calcBreakeven(coordinateList);
		
		response.put("breakevenpoints", breakEvenPoints);
		
		double maxProfit = dlogic.calcMaxProfit(coordinateList);
		double maxLoss = dlogic.calcMaxLoss(coordinateList);
		
		response.put("maxprofit", maxProfit);
		response.put("maxloss", maxLoss);
		
		CalculatePLLogic payoffCalc = new CalculatePLLogic();
		
		if ((coordinateList.get(coordinateList.size()-2).getX()<breakEvenPoints.get(breakEvenPoints.size()-1))) {
			coordinateList.get(coordinateList.size()-1).setX(1.2*breakEvenPoints.get(breakEvenPoints.size()));
			double netPL = 0.0;
			for (Holding holding:holdingList) {
				netPL+= payoffCalc.calculatePL(new Holding(holding.getType(),holding.getPosition(),holding.getStrikePrice(),holding.getSymbol(),holding.getExpiryDate(),coordinateList.get(coordinateList.size()-1).getX(),holding.getVolatility(),holding.getLotSize(),holding.getNumLots(),holding.getPremium(),coordinateList.get(coordinateList.size()-1).getX(),holding.getSpotPrice())).getPayoff(); 
			}
			coordinateList.get(coordinateList.size()-1).setY(netPL);
		}
		else {
			coordinateList.get(coordinateList.size()-1).setX(coordinateList.get(coordinateList.size()-2).getX());
			double netPL = 0.0;
			for (Holding holding:holdingList) {
				netPL+= payoffCalc.calculatePL(new Holding(holding.getType(),holding.getPosition(),holding.getStrikePrice(),holding.getSymbol(),holding.getExpiryDate(),coordinateList.get(coordinateList.size()-1).getX(),holding.getVolatility(),holding.getLotSize(),holding.getNumLots(),holding.getPremium(),coordinateList.get(coordinateList.size()-1).getX(),holding.getSpotPrice())).getPayoff(); 
			}
			coordinateList.get(coordinateList.size()-1).setY(netPL);
		}
		
		response.put("coordinatelist", coordinateList);
		response.put("message","Analysis done");
		response.put("url", url);
		
		return response;
	}
}
