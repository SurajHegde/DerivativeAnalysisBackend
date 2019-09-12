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

@Path("/create")
public class UserAuthCreateAccount {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public JSONObject createAccount(JSONObject incomingData) {
		List<String> message = null;
		String url;
		List <Holding> userHoldings = null;
		String emailId = (String) incomingData.get("emailId");
		String firstName = (String) incomingData.get("firstName");
		String lastName = (String) incomingData.get("lastName");
		String password = (String) incomingData.get("password");
		String confirmPassword = (String) incomingData.get("confirmPassword");

		UserDAOImpl userDao = new UserDAOImpl();
		List <String> output = userDao.createAccount(emailId, firstName, lastName, password, confirmPassword);
		boolean validateCreate = false;
		for(String errorMessages : output) {
			if(errorMessages.equals("User was registered successfully"))
				validateCreate = true;
		}
		message = output;
		if(validateCreate) {
			url = "/app/dashboard";
		} else {
			url = "/login";
		}

		JSONObject response = new JSONObject();
		response.put("message", message);
		response.put("url", url);
		response.put("uniqueIdentifier", emailId);
		response.put("firstName", firstName);
		response.put("lastNname", lastName);
		response.put("userHolding", userHoldings);
		response.put("error",validateCreate);
		return(response);
	} 

}
