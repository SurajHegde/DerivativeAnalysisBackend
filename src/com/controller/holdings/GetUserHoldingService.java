package com.controller.holdings;

import org.json.simple.JSONObject;

import com.dao.DerivativeDAO;
import com.dao.DerivativeDAOImpl;
import com.pojo.Holding;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class GetUserHoldingService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject getUserHoldings(JSONObject incomingData) {
		
		String message = "",url;
		List<Holding> allUserHoldings = new ArrayList<Holding>();
		String emailId = (String)incomingData.get("emailId");
		DerivativeDAO derivativeDao = new DerivativeDAOImpl();
		allUserHoldings = derivativeDao.getAllUserHoldings(emailId);
//		for(Holding holdings:allUserHoldings){
//			System.out.println(holdings.getSymbol());
//		}
		
		if(allUserHoldings.isEmpty()) {
			message = "You have no holdings";
		}
		url = "../app/holdings";
		JSONObject response = new JSONObject();
		response.put("userHoldings",allUserHoldings);
		response.put("message", message);
		response.put("url", url);
		return response;
	}
}
