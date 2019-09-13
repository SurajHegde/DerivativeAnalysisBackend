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
	//@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject valueByUser(String emailId) {
		
		JSONObject response = new JSONObject();
		return response;
	}
}
