package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.connection.MyConnection;
import com.pojo.Password;
import com.pojo.Holding;
import com.logic.UserHoldingExtraFunctions;
public class UserDAOImpl implements UserDAO {

	@Override
	public boolean emailExists(String emailId) {
		
		// TODO Auto-generated method stub
		String emailExists = "select * from users where emailId = ?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(emailExists);){
			ps.setString(1, emailId);
			ResultSet set = ps.executeQuery();

			if (set.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUser(String emailId, String firstName, String lastName, String password) {
		// TODO Auto-generated method stub
		String addUser = "insert into users values (?,?,?,?,?)";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(addUser);){
			String salt = Password.generateSalt(512).get();
			String hashPassword = Password.hashPassword(password, salt).get();
			ps.setString(1, emailId);
			ps.setString(2, hashPassword);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5,salt);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public List<String> createAccount(String emailId,String firstName,String lastName,String password,String confirmPassword) {
		// TODO Auto-generated method stub
		List<String> error = new ArrayList<String>();
		List<String> success = new ArrayList<String>();
		try {
			if (emailExists(emailId)) {
				error.add("Email ID already exists");
			}

			if(emailId == "") {
				error.add("Email ID cannot be empty");
			}
			if (firstName == "") {
				error.add("First name cannot be empty");
			}
			if (lastName == "") {
				error.add("Last name cannot be empty");
			}
			if (password.length() < 8) {
				error.add("Password length cannot be lesser than 8");
			}
			if (!password.equals(confirmPassword)) {
				error.add("Passwords do not match");
			}
			if(!error.isEmpty()) {
				return error;
			}
			if (addUser(emailId,firstName,lastName,password)) {
				success.add("User was registered successfully");
				return success;
			}
			else {
				error.add("Registration was not completed!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	@Override
	public String login(String emailId,String password) {
		// TODO Auto-generated method stub
		if (!emailExists(emailId)) {
			return "No account with such an email exists";
		}
		else {
			String emailPasswordCombo = "select firstname,lastname,password,salt from users where emailId = ?";
			try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(emailPasswordCombo);){
				ps.setString(1, emailId);
				ResultSet set = ps.executeQuery();

				while (set.next()) {
					String salt = set.getString("salt");
					String databasePassword = set.getString("password");
					boolean verifyPassword = Password.verifyPassword(password, databasePassword, salt);
					if (!verifyPassword) {
						return "Invalid credentials";
					}
					else {
						return set.getString("firstname") + " " + set.getString("lastname");
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	

	@Override
	public List<Holding> getAllHoldings(String emailId) {
		// TODO Auto-generated method stub
		List<Holding> allUserHoldings = new ArrayList<Holding>();
		String GET_HOLDINGS_USER = "select * from holdings where emailid = ?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_HOLDINGS_USER);){
			ps.setString(1,emailId);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				Holding userHolding = new Holding();
				userHolding.setAvgPrice(set.getDouble("avg_price"));
				userHolding.setSymbol(set.getString("symbol"));
				userHolding.setType(set.getString("type"));
				userHolding.setPosition(set.getString("position"));
				userHolding.setExpiryDate(set.getString("expiry_date"));
				userHolding.setNumLots(set.getInt("lots"));
				userHolding.setLotSize(set.getInt("lot_size"));
				userHolding.setPremium(set.getDouble("premium"));
				userHolding.setStrikePrice(set.getDouble("strike_price"));
				userHolding.setLtp(set.getDouble("lcp"));
				allUserHoldings.add(userHolding);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allUserHoldings;
	}
	public List<Double> getGain(List<Holding> userHoldings){
		List<Double> gainList = new ArrayList<Double>();
		UserHoldingExtraFunctions extraFun = new UserHoldingExtraFunctions();
		for(Holding holding : userHoldings) {
			double spotPrice  = extraFun.getSpotPrice(holding);
			holding.setSpotPrice(spotPrice);
			gainList.add(((spotPrice - holding.getLtp())*holding.getNumLots()*holding.getLotSize())/holding.getLtp());
		}
		return gainList;
	}
	public List<Double> getGainPercentage(List<Holding> userHoldings){
		List<Double> gainPercentageList = new ArrayList<Double>();
		UserHoldingExtraFunctions extraFun = new UserHoldingExtraFunctions();
		for(Holding holding : userHoldings) {
			double spotPrice = extraFun.getSpotPrice(holding);
			holding.setSpotPrice(spotPrice);
			gainPercentageList.add(((spotPrice - holding.getLtp())*holding.getNumLots()*holding.getLotSize())/holding.getLtp());
		}
		return gainPercentageList;
	}
}
