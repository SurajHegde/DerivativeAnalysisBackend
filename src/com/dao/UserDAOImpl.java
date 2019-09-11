package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.connection.MyConnection;
import com.pojo.Password;
import com.pojo.Holding;

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
	public String createAccount(String emailId,String firstName,String lastName,String password,String confirmPassword) {
		// TODO Auto-generated method stub
		String error = "";
		try {
			if (emailExists(emailId)) {
				error += "Email ID already exists\n";
			}

			if(emailId == "") {
				error += "Email ID cannot be empty\n";
			}
			if (firstName == "") {
				error += "First name cannot be empty\n";
			}
			if (lastName == "") {
				error += "Last name cannot be empty\n";
			}
			if (password.length() < 8) {
				error += "Password length cannot be lesser than 8\n";
			}
			if (!password.equals(confirmPassword)) {
				error += "Passwords do not match\n";
			}
			if(!error.equals("")) {
				return error;
			}
			if (addUser(emailId,firstName,lastName,password)) {
				return "User was registered successfully\n";
			}
			else {
				return " Registration was not completed";
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
						return "Login Successful";
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
	
}
