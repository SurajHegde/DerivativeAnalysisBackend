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
public class UserAuth {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@Path("/CreateAccount")
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
		response.put("name", firstName+lastName);
		response.put("userHolding", userHoldings);
		
		return(response);
	}
	
	@Path("/LoginCheck")
	public JSONObject login(JSONObject incomingData) {
		String message;
		String url;
		List <Holding> userHoldings = null;
		String emailId = (String) incomingData.get("emailId");
		String password = (String) incomingData.get("password");
		
		UserDAOImpl userDao = new UserDAOImpl();
		String output = userDao.login(emailId, password);
		
		if(output.equals("Invalid credentials") || output.equals("No account with such an email exists")) {
			message = output;
			url = "/login";
		} else {
			message = "Login Successful";
			url = "/app/dashboard";
			userHoldings = userDao.getAllHoldings(emailId);
		}
		
		JSONObject response = new JSONObject();
		response.put("message", message);
		response.put("url", url);
		response.put("uniqueIdentifier", emailId);
		response.put("name", output);
		response.put("userHolding", userHoldings);
		
		return(response);
	}
}
