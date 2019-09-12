package com.testing;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dao.DerivativeDAOImpl;
import com.pojo.Holding;

class DerivativeDAOImplTest {

	@Test
	void testCheckUserHolding() {
		//fail("Not yet implemented");
	
	}

	@Test
	void testAddUserHolding_new() {
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		//boolean actual= daoImpl.addUserHolding("utkarshsharmaas@gmail.com","CE","LONG",2150,"RELIANCE","29-OCT-19",200,11.4,6.55,5,105.25);
		boolean actual= daoImpl.addUserHolding("chandaknihal231@gmail.com", "CE","SHORT",2100, "HDFCBANK", "02-NOV-19",200,11.4, 150,25, 270);
		assertEquals(true, actual);
	}
	
	@Test
	void testAddUserHolding_existing() {
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		boolean actual= daoImpl.addUserHolding("utkarshsharmaas@gmail.com","FUT","LONG",2000,"HDFCBANK","02-NOV-19",200,11.4,6.55,5,105.75);
		//boolean actual= daoImpl.addUserHolding("sailimaye@gmail.com", "CE","SHORT",2000, "HDFCBANK", "02-NOV-19",200,11.4, 7.25,4, 100.25);
		assertEquals(true, actual);
	}
	@Test
	void testAddUserHolding_existing1() {
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		boolean actual= daoImpl.addUserHolding("utkarshsharmaas@gmail.com","FUT","LONG",2000,"HDFCBANK","02-NOV-19",200,11.4,6.55,4,105.25);
		
		//boolean actual= daoImpl.addUserHolding("sailimaye@gmail.com", "CE","SHORT",2000, "HDFCBANK", "02-NOV-19",200,11.4, 7.25,4, 100.25);
		assertEquals(true, actual);
	}
	@Test
	void testAddUserHolding_existing2() {
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		boolean actual= daoImpl.addUserHolding("utkarshsharmaas@gmail.com","CE","SHORT",2000,"HDFCBANK","02-NOV-19",200,11.4,6.55,4,105.25);
		
		//boolean actual= daoImpl.addUserHolding("sailimaye@gmail.com", "CE","SHORT",2000, "HDFCBANK", "02-NOV-19",200,11.4, 7.25,4, 100.25);
		assertEquals(true, actual);
	}
	@Test
	void testGetSpecificDerivative() {
	
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		List<Holding> actual= daoImpl.getSpecificDerivative("HDFCBANK");
		Holding h= actual.get(0);
		assertEquals(207.75, h.getLtp());
		
	}

	@Test
	void testGetLotSize() {
		DerivativeDAOImpl daoImpl= new DerivativeDAOImpl();
		double actual= daoImpl.getLotSize("HDFCBANK");
		assertEquals(250, actual);
	}


}
