package com.testing;

import com.pojo.Holding;
import static org.junit.jupiter.api.Assertions.*;

import com.dao.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import org.junit.jupiter.api.Test;

class UserDAOImplTest {

	
	@Test
	void testEmailEmpty() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<String> actual= daoImpl.createAccount("", "Utkarsh", "Sharma", "161219971", "161219971");
		assertEquals(1, actual.size());
		
	}
	@Test
	void testFirstNameEmpty() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<String> actual= daoImpl.createAccount("utkarsh@123", "", "Sharma", "161219971", "161219971");
		assertEquals(1, actual.size());
		
	}
	@Test
	void testLastNameEmpty() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<String> actual= daoImpl.createAccount("utkarsh@12","", "", "161219971", "161219971");
		assertEquals(2, actual.size());
		
	}
	
	@Test
	void testPassNotMatch() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<String> actual= daoImpl.createAccount("utkarsh@1", "", "", "161219971", "16121997");
		assertEquals(3, actual.size());
		
	}

	@Test
	void testPassLength() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<String> actual= daoImpl.createAccount("utkarsh@gm", "Utkarsh", "Sharma", "1612", "1612");
		assertEquals(1, actual.size());
		
	}

	@Test
	void testGetAllHoldings() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<Holding> list = daoImpl.getAllHoldings("suraj@123");
		//int actual= list.size();

		Holding h1=list.get(0);
		assertEquals(250, h1.getNumLots());

	}
	
	@Test
	void testLoginEmail() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual=daoImpl.login("ni@gmail.com", "nihal1234");
		assertEquals("No account with such an email exists", actual);
	}
	
	@Test
	void testLoginPassword() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual=daoImpl.login("ut@gmail.com", "16121997");
		assertEquals("Utkarsh Sharma", actual);
	}
	@Test
	void testLogin() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual=daoImpl.login("suraj@gmail.com", "25021997");
		assertEquals("Login Successful", actual);
	}
	
	@Test
	void testAddUser() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		boolean actual=daoImpl.addUser("suraj@gmail.com", "Suraj", "Hegde", "25021997");
		assertEquals(true, actual);
	}
	@Test
	void testAddUser_Negative() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		boolean actual=daoImpl.addUser("", "", "", "");
		assertEquals(false, actual);
	}
	
	
	
	@Test
	void testEmailExists() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		boolean actual= daoImpl.emailExists("nihal@gmail.com");
		assertEquals(true, actual);
	}

	

	@Test
	void testEmailExists_Negative() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		boolean actual= daoImpl.emailExists("hal@gmail.com");
		assertEquals(false, actual);
	}

}