package com.controller.holdings;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import com.dao.DerivativeDAOImpl;

@Path("/valuebyuser")
public class GetHoldingsValueByUser {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject valueByUser(JSONObject incomingData) {
		String emailId = (String) incomingData.get("emailId");
		HashMap<String,Double> userValue = new HashMap<String,Double>();
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		userValue = dl.getValueUser(emailId);
		JSONObject response = new JSONObject();
		response.put("totalValue", userValue);
		return response;
	}
}
