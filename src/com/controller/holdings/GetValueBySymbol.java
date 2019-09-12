package com.controller.holdings;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import com.dao.DerivativeDAOImpl;
import com.dao.DerivativeDAOImpl;

@Path("/valuebysymbol")

public class GetValueBySymbol {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject getValueByHolding(JSONObject incomingData) {
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		JSONObject response = new JSONObject();
		String emailId = (String)incomingData.get("userEmail");
		String symbol = (String)incomingData.get("symbol");
		HashMap<String,Double> instrumentValue = new HashMap<String,Double>();
		instrumentValue = dl.getValueInstrument(emailId, symbol);
		response.put("instrumentValue", instrumentValue);
		return response;
	}
	
}
