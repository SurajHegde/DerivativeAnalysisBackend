package com.dao;
import java.util.List;

import com.pojo.Holding;
public interface UserDAO {
	String createAccount(String emailId,String firstName,String lastName,String password,String confirmPassword);
	String login(String emailId,String password);
	List<Holding> showAll();
	boolean logout();
}
