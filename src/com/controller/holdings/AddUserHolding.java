package com.controller.holdings;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import com.dao.DerivativeDAOImpl;
import com.pojo.Holding;
import com.sun.research.ws.wadl.Response;
import com.dao.UserDAOImpl;
@Path("/senduserinput")
public class AddUserHolding {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject addHolding(JSONObject incomingData) {
		String message = "failure";
		String symbol = (String) incomingData.get("symbol");
		String expiryDate = (String) incomingData.get("expiryDate");
		String position = (String) incomingData.get("position");
		String type = (String) incomingData.get("type");
		int numLots = Integer.parseInt((String) incomingData.get("numLots"));
		double price = Double.parseDouble((String) incomingData.get("price"));
		int lotSize = Integer.parseInt((String) incomingData.get("lotSize"));
		double strikePrice = Double.parseDouble((String) incomingData.get("strikePrice"));
		String emailId = (String) incomingData.get("userId");
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		double lcp = dl.getLCP(symbol, type, expiryDate, strikePrice);
		boolean rows = dl.addUserHolding(emailId, type, position, strikePrice, symbol, expiryDate, lotSize, lcp, price, numLots,price);
		UserDAOImpl userDao = new UserDAOImpl();
		JSONObject response = new JSONObject();
		if(rows) {
			List<Holding> userHoldings = dl.getAllUserHoldings(emailId);
			List<Double> gain = userDao.getGain(userHoldings);
			List<Double> gainPercentageList = userDao.getGainPercentage(userHoldings);
			message = "Success";
			response.put("userHoldings", userHoldings);
			response.put("gain",gain);
			response.put("gainPercentage", gainPercentageList);
			return response;
		}
		response.put("message", message);
		//public boolean addUserHolding(String emailId,String type, String position, double strikePrice, String symbol,String expiryDate,double lotSize, double lcp,double premium,int numLots,double spotPrice) 
		return response;
	}
}
