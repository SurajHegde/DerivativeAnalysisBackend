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
		String actual= daoImpl.createAccount("", "Utkarsh", "Sharma", "161219971", "161219971");
		assertEquals("Email ID cannot be empty\n", actual);
		
	}
	@Test
	void testFirstNameEmpty() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual= daoImpl.createAccount("utkarsh@123", "", "Sharma", "161219971", "161219971");
		assertEquals("First name cannot be empty\n", actual);
		
	}
	@Test
	void testLastNameEmpty() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual= daoImpl.createAccount("utkarsh@12", "Utkarsh", "", "161219971", "161219971");
		assertEquals("Last name cannot be empty\n", actual);
		
	}
	
	@Test
	void testPassNotMatch() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual= daoImpl.createAccount("utkarsh@1", "Utkarsh", "Sharma", "161219971", "16121997");
		assertEquals("Passwords do not match\n", actual);
		
	}

	@Test
	void testPassLength() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual= daoImpl.createAccount("utkarsh@gm", "Utkarsh", "Sharma", "1612", "1612");
		assertEquals("Password length cannot be lesser than 8\n", actual);
		
	}

	@Test
	void testGetAllHoldings() {
		//fail("Not yet implemented");
		UserDAOImpl daoImpl= new UserDAOImpl();
		List<Holding> list = daoImpl.getAllHoldings("suraj@123");
		int actual= list.size();

		assertEquals(2, actual);
		
//		List<Holding> expected= new ArrayList<>();
//		Holding h1 = new Holding();
//		h1.setAvgPrice(105.25);
//		h1.setSymbol("YESBANK");
//		h1.setType("CE");
//		h1.setPosition("SHORT");
//		java.util.Date newDate = new Date();
//		h1.setExpiryDate('19-OCT-2019');
//		h1.setNumLots(260);
//		h1.setPremium(6.55);
//		expected.add(h1);
//		
//		//for(Holding h1:)
		
		
	
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
		String actual=daoImpl.login("nihal@gmail.com", "nihal");
		assertEquals("Invalid credentials", actual);
	}
	@Test
	void testLogin() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		String actual=daoImpl.login("nihal@gmail.com", "nihal1234");
		assertEquals("Nihal Chandak", actual);
	}
	
	@Test
	void testAddUser() {
		UserDAOImpl daoImpl= new UserDAOImpl();
		boolean actual=daoImpl.addUser("ut@gmail.com", "Utkarsh", "Sharma", "16121997");
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