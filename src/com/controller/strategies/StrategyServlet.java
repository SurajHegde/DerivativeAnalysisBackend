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
		String symbol = (String) incomingData.get("security");
		String views = (String) incomingData.get("views");
		String expiry_date = (String) incomingData.get("expiryDate");
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
				l.addAll(s.BullCallSpread(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Long Call Butterfly":
				l.addAll(s.LongCallButterfly(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Bear Put Spread":
				l.addAll(s.BearPutSpread(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Long Straddle":
				l.addAll(s.LongStraddle(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Short Straddle":
				l.addAll(s.ShortStraddle(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Long Call Condor":
				l.addAll(s.LongCallCondor(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Short Call Condor":
				l.addAll(s.ShortCallCondor(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Short Call Butterfly":
				l.addAll(s.ShortCallButterfly(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Long Strangle":
				l.addAll(s.LongStrangle(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Short Strangle":
				l.addAll(s.ShortStrangle(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
					
			case "Long Call":
				l.addAll(s.LongCall(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Short Put":
				l.addAll(s.ShortPut(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Bull Put Spread":
				l.addAll(s.BullPutSpread(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Long Combo":
				l.addAll(s.LongCombo(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Bear Long Put":
				l.addAll(s.BearLongPut(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Bear Short Call":
				l.addAll(s.BearShortCall(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			case "Bear Call Spread":
				l.addAll(s.BearCallSpread(symbol, target, expiry_date));
				System.out.println(l);
				System.out.println(l.size());
				break;
				
			}
			
			
		}
		
		for(int i=0;i<l.size(); i++)
		{
			System.out.println(l.get(i));
		}
		
		System.out.println(l.size());
		
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
