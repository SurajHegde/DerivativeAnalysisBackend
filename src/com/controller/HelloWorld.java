package com.controller;

import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.UserDAOImpl;

@Path("/check")

public class HelloWorld {

	// TODO Auto-generated method stub
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject login(JSONObject incomingData) {
		
		String message;
		String url;
		String emailId = (String) incomingData.get("emailIDLogin");
		String password = (String) incomingData.get("passwordLogin");
		
		System.out.println(emailId+": "+password);
		
		UserDAOImpl userDao = new UserDAOImpl();
		String output = userDao.login(emailId,password);
		
		if(output.equals("Invalid credentials") || output.equals("No account with such an email exists")) {
			message = output;
			url = "../login";
		} else {
			message = "Login Successful";
			url = "../app/dashboard";
		}
		
		JSONObject response = new JSONObject();
		response.put("message", message);
		response.put("url", url);
		
		return(response);
		
	
	}
}
