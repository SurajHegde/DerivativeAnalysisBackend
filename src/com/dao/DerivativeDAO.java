package com.dao;

import com.logic.Pair;
import com.pojo.Holding;

import java.sql.ResultSet;
import java.util.List;

public interface DerivativeDAO {

	public List<Holding> getAllUserHoldings(String emailId);
	public ResultSet checkUserHolding(String emailId,String symbol,String type,String expiryDate,double strikePrice);
	public boolean addUserHolding(String emailId,String type, String position, double strikePrice, String symbol,String expiryDate,double lotSize, double lcp,double premium,int numLots,double spotPrice);
	public boolean updateUserHolding(String emailId,String type,String symbol,String position,String expiryDate,double strikePrice,int numLots,double spotPrice);
	public int deleteHolding(String emailId,String symbol,String type,String expiryDate,double strikePrice);
	public List<Holding> getSpecificDerivative(String symbol);
	
}