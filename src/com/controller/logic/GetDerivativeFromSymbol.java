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
import com.pojo.Holding;

@Path ("/derivativeList")
public class GetDerivativeFromSymbol {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject getDerivative(JSONObject incomingData) {
				
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		String symbol = (String) incomingData.get("value");
		List<Holding> allHoldingsWithSymbol = dl.getSpecificDerivative(symbol);
		JSONObject response = new JSONObject();
		response.put("derivativeList",allHoldingsWithSymbol);
		return response;
	}
}
