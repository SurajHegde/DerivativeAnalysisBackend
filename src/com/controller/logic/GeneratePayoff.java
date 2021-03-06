package com.controller.logic;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.dao.UserDAOImpl;
import com.logic.DerivativeLogic;
import com.logic.Pair;
import com.pojo.Holding;

@Path("/generatepayoff")
public class GeneratePayoff {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject generatePayoff(JSONObject incomingData) {
		
		String message;
		String url = "/app/analysis";
		List<Holding> holdingList = (List) incomingData.get("holdingList");
		
//		List<JSONObject> jsonObjectList = (List) incomingData.get("holdingList");
//		for (JSONObject jsonObject:jsonObjectList) {
//			Holding holding = new Holding();
//			String[] symbol = ((String)jsonObject.get("instrument")).split(" ");
//			
//			if (symbol.length==2) {
//				holding.setPosition((String)jsonObject.get("type"));
//				holding.setNumLots((int)jsonObject.get("quantity"));
//				holding.setAvgPrice((double)jsonObject.get("price"));
//				holding.setStrikePrice(0);
//				holding.setLotSize((int)jsonObject.get("lotsize"));
//			}
//			else {
//				holding.setPremium((double)jsonObject.get("price"));
//				holding.setPosition((String)jsonObject.get("type"));
//				holding.setNumLots((int)jsonObject.get("quantity"));
//				holding.setStrikePrice(Double.parseDouble(symbol[1]));
//				holding.setLotSize((int)jsonObject.get("lotsize"));
//			}
//			
//			holdingList.add(holding);	
//		}
//		
		JSONObject response = new JSONObject();
		
		DerivativeLogic dlogic = new DerivativeLogic();
		List<Pair> coordinateList = dlogic.generatePayoff(holdingList);
		
		response.put("coordinatelist", coordinateList);
		
		List<Double> breakEvenPoints = dlogic.calcBreakeven(coordinateList);
		
		response.put("breakevenpoints", breakEvenPoints);
		
		double maxProfit = dlogic.calcMaxProfit(coordinateList);
		double maxLoss = dlogic.calcMaxLoss(coordinateList);
		
		response.put("maxprofit", maxProfit);
		response.put("maxloss", maxLoss);
		
		response.put("message","Analysis done");
		response.put("url", url);
		
		return response;
	}
}
