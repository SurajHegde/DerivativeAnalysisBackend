package com.controller.login;

import org.json.simple.JSONObject;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.UserDAOImpl;
import com.pojo.Holding;

@Path("/UserAuth")
public class UserAuthCreateAccount {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public JSONObject createAccount(JSONObject incomingData) {
		String message;
		String url;
		List <Holding> userHoldings = null;
		String emailId = (String) incomingData.get("emailId");
		String firstName = (String) incomingData.get("firstName");
		String lastName = (String) incomingData.get("lastName");
		String password = (String) incomingData.get("password");
		String confirmPassword = (String) incomingData.get("confirmPassword");
		
		UserDAOImpl userDao = new UserDAOImpl();
		String output = userDao.createAccount(emailId, firstName, lastName, password, confirmPassword);
		
		if(!output.equals("User was registered successfully\n")) {
			message = output;
			url = "/login";
		} else {
			message = "User was registered successfully";
			url = "/app/dashboard";
		}
		
		JSONObject response = new JSONObject();
		response.put("message", message);
		response.put("url", url);
		response.put("uniqueIdentifier", emailId);
		response.put("firstName", firstName);
		response.put("lastNname", lastName);
		response.put("userHolding", userHoldings);
		
		return(response);
	}
	
}
