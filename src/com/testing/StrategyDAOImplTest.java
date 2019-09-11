package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.StrategyDAOImpl;
import com.pojo.Holding;
import com.pojo.Strategy;

class StrategyDAOImplTest {

	@Test
	void testGetStrategies() {
	//	fail("Not yet implemented");
		StrategyDAOImpl daoImpl = new StrategyDAOImpl();
		Strategy strategy= new Strategy();
		List<Strategy> actual= daoImpl.getStrategies("Bullish");
		Strategy st= actual.get(0);
		
		assertEquals("Collar",st.getStrategyName());
	}

	@Test
	void testGetHolding() {
		//fail("Not yet implemented");
		StrategyDAOImpl daoImpl = new StrategyDAOImpl();
		List<Holding> holdings= daoImpl.getHolding("HDFCBANK", "CE","26-SEP-19");
		Holding h= holdings.get(0);
		assertEquals(2060, h.getStrikePrice());
		
		
	}

}
