package com.dao;
import java.util.List;

import com.pojo.Holding;
public interface UserDAO {
	boolean createAccount();
	boolean login();
	List<Holding> showAll();
	boolean logout();
}
