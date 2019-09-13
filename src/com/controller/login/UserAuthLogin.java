package com.controller.login;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.dao.DerivativeDAOImpl;
import com.dao.UserDAOImpl;
import com.logic.UserHoldingExtraFunctions;
import com.pojo.Holding;

@Path("/login")
public class UserAuthLogin {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public JSONObject login(JSONObject incomingData) {
		String message;
		String url;
		List <Holding> userHoldings = new ArrayList<Holding>();
		String emailId = (String) incomingData.get("emailId");
		String password = (String) incomingData.get("password");
		boolean error = false;
		UserDAOImpl userDao = new UserDAOImpl();
		String output = userDao.login(emailId, password);
		
		if(output.equals("Invalid credentials") || output.equals("No account with such an email exists")) {
			message = output;
			url = "/login";
		} else {
			message = "Login Successful";
			url = "/app/dashboard";
			userHoldings = userDao.getAllHoldings(emailId);
			error = true;
		}
		
		UserHoldingExtraFunctions extraFun = new UserHoldingExtraFunctions();
		List<Double> gainList = new ArrayList<>();
		List<Double> gainPercentageList = new ArrayList<>();
		for (Holding holding:userHoldings) {
			double spotPrice  = extraFun.getSpotPrice(holding);
			holding.setSpotPrice(spotPrice);
			Double gain = new Double(((spotPrice - holding.getLtp())*holding.getNumLots()*holding.getLotSize())/holding.getLtp());
			Double gainPercentage = new Double(((spotPrice - holding.getLtp())*100)/holding.getLtp());
			gainList.add(BigDecimal.valueOf(gain).setScale(2, RoundingMode.HALF_UP).doubleValue());
			gainPercentageList.add(BigDecimal.valueOf(gainPercentage).setScale(2, RoundingMode.HALF_UP).doubleValue());
		}

		HashMap<String,HashMap<String,Double>> userValue = new HashMap<String,HashMap<String,Double>>();
		DerivativeDAOImpl dl = new DerivativeDAOImpl();
		userValue = dl.getChartComponent(emailId);
		
		JSONObject response = new JSONObject();
		response.put("message", message);
		response.put("url", url);
		response.put("uniqueIdentifier", emailId);
		response.put("firstName", output.split(" ")[0]);
		response.put("lastName", output.split(" ")[1]);
		response.put("userHolding", userHoldings);
		response.put("gainList", gainList);
		response.put("gainPercentageList", gainPercentageList);
		response.put("error", error);
		response.put("dashBoardComponent", userValue);
		return(response);
	}
}
