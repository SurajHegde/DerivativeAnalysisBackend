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
		String url = "/strategyservlet";
		String symbol = (String) incomingData.get("symbol");
		String views = (String) incomingData.get("views");
		String expiry_date = (String) incomingData.get("targetDate");
		String temp=(String)incomingData.get("target");
		double target = Double.parseDouble(temp);		
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
				
			case "Long Straddle":
				l = s.LongStraddle(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Short Straddle":
				l = s.ShortStraddle(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Long Call Condor":
				l = s.LongCallCondor(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Short Call Condor":
				l = s.ShortCallCondor(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Short Call Butterfly":
				l = s.ShortCallButterfly(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Long Strangle":
				l = s.LongStrangle(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Short Strangle":
				l = s.ShortStrangle(symbol, target, expiry_date);
				System.out.println(l);
				break;
					
			case "Long Call":
				l = s.LongCall(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Short Put":
				l = s.ShortPut(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bull Put Spread":
				l = s.BullPutSpread(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Long Combo":
				l = s.LongCombo(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bear Long Put":
				l = s.BearLongPut(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bear Short Call":
				l = s.BearShortCall(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bear Call Spread":
				l = s.BearCallSpread(symbol, target, expiry_date);
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
