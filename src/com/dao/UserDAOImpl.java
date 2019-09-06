package com.dao;

import java.util.List;

import com.pojo.Holding;

public class UserDAOImpl implements UserDAO {

	@Override
	public String createAccount(String emailId,String firstName,String lastName,String password,String confirmPassword) {
		// TODO Auto-generated method stub
		return "false";
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
