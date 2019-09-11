package com.controller.holdings;

import javax.ws.rs.Path;

import org.json.simple.JSONObject;

import com.sun.research.ws.wadl.Response;

@Path("/senduserinput")
public class AddUserHolding {
	String message,url;
	public JSONObject addHolding(JSONObject incomingData) {
		JSONObject strategy = (JSONObject) incomingData.get("holding");
		String strategyValues = (String) strategy.get("strategy");
		String[] getStrategy = strategyValues.split(" ");
		String expiryDate = getStrategy[0];
		Double strikePrice = Double.parseDouble(getStrategy[1]);
		strikePrice = strikePrice.doubleValue();
		String type = getStrategy[2];
		String doublePremium = getStrategy[3].replaceAll("[()]", "");
		Double premium = Double.parseDouble(doublePremium);
		premium = premium.doubleValue();
		JSONObject response = new JSONObject();
		response.put("premium",premium);
		return response;
	}
}
