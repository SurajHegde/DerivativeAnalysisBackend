package com.controller.strategies;

import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.UserDAOImpl;
import com.logic.StrategyLogic;
import com.pojo.Derivative;
import com.pojo.Holding;
import com.pojo.Strategy;
import com.pojo.StrategyOutput;

@Path("/strategyservlet")

public class StrategyServlet {

	// TODO Auto-generated method stub
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject login(JSONObject incomingData) {
		
		String message;
		String url;
		String symbol = (String) incomingData.get("symbol");
		String views = (String) incomingData.get("views");
		String expiry_date = (String) incomingData.get("targetDate");
		double target = (double) incomingData.get("target");
				
		List<Strategy> strategies = new ArrayList<>();
		StrategyLogic s = new StrategyLogic();
		strategies = s.getStrategy(symbol,views,expiry_date,target);
			
		List<StrategyOutput> l = new ArrayList<>();
		
		for(int i=0;i<strategies.size();i++)
		{
			
			switch(strategies.get(i).getStrategyName())
			{
			case "Bull Call Spread":
				l = s.BullCallSpread(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Long Call Butterfly":
				l = s.LongCallButterfly(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bear Put Spread":
				l = s.BearPutSpread(symbol, target, expiry_date);
				System.out.println(l);
				break;
			}
		}
		
		for(int i=0;i<l.size(); i++)
		{
			System.out.println(l.get(i));
		}
		
//		if(output.equals("Invalid credentials") || output.equals("No account with such an email exists")) {
//			message = output;
//			url = "../login";
//		} else {
//			message = "Login Successful";
//			url = "../app/dashboard";
//		}
		
		JSONObject response = new JSONObject();
//		response.put("message", message);
//		response.put("url", url);
		response.put("strategies", l);
		return(response);
		
	
	}
}
