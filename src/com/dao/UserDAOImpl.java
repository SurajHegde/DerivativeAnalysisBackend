package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.connection.MyConnection;

import com.pojo.Holding;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public boolean emailExists(String emailId) {
		// TODO Auto-generated method stub
		String emailExists = "select * from users where emailId = ?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(emailExists);){
			ps.setString(1, emailId);
			ResultSet set = ps.executeQuery();
			
			if (set == null) {
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
		String addUser = "insert into users values (?,?,?,?)";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(addUser);){
			ps.setString(1, emailId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, password);
			
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
		try {
			if (emailExists(emailId)) {
				return "Email ID already exists";
			}
			else if (firstName == "") {
				return "First name cannot be empty";
			}
			else if (lastName == "") {
				return "Last name cannot be empty";
			}
			else if (password.length( )< 8) {
				return "Password length cannot be lesser than 8";
			}
			else if (!password.equals(confirmPassword)) {
				return "Passwords do not match";
			}
			else if (addUser(emailId,firstName,lastName,password)) {
				return "User was registered successfully";
			}
			else {
				return "Registration was not completed at this time";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String login(String emailId,String password) {
		// TODO Auto-generated method stub
		return "false";
	}

	@Override
	public List<Holding> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

}
