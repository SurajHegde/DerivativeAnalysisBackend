package com.controller.login;

import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.UserDAOImpl;

@Path("/UserAuth")
public class UserAuth {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	@Path("/CreateAccount")
	public JSONObject createAccount(JSONObject incomingData) {
		String message;
		String url;
		String emailId = (String) incomingData.get("emailID");
		String firstName = (String) incomingData.get("emailID");
		String lastName = (String) incomingData.get("emailID");
		String password = (String) incomingData.get("password");
		String confirmPassword = (String) incomingData.get("emailID");
		
		UserDAOImpl userDao = new UserDAOImpl();
		String output = userDao.createAccount(emailId, firstName, lastName, password, confirmPassword);
	}
}
