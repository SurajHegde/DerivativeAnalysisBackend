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
		String error = "";
		try {
			if (emailExists(emailId)) {
<<<<<<< HEAD
				error += " Email ID already exists\n";
=======
				return "Email ID already exists";
>>>>>>> 69eb14e71b015e36b707a91ac6d189c8908a127d
			}
			
			if(emailId == "") {
				error += "Email ID cannot be empty\n";
			}
			if (firstName == "") {
				error += " First name cannot be empty\n";
			}
			if (lastName == "") {
				error += " Last name cannot be empty\n";
			}
			if (password.length() < 8) {
				error += " Password length cannot be lesser than 8\n";
			}
			if (!password.equals(confirmPassword)) {
				error += " Passwords do not match\n";
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
