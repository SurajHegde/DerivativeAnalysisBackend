package com.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.connection.MyConnection;
import com.pojo.Holding;

public class UserHoldingExtraFunctions {
	public double getSpotPrice(Holding userHolding) {
		double spotPrice = 0.0;
		String FIND_SPOT_PRICE = "SELECT LTP FROM DERIVATIVES WHERE SYMBOL = ? AND TYPE = ? AND EXPIRY_DATE = ? AND STRIKE_PRICE = ?";
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(FIND_SPOT_PRICE)){
			ps.setString(1, userHolding.getSymbol());
			ps.setString(2, userHolding.getType());
			ps.setString(3, userHolding.getExpiryDate());
			ps.setDouble(4, userHolding.getStrikePrice());
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				spotPrice = set.getDouble("LTP");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return spotPrice;
	}
	public double getSpotPrice(String symbol,String type,String expiryDate,double strikePrice) {
		double spotPrice = 0.9;
		String FIND_SPOT_PRICE = "SELECT LTP FROM DERIVATIVES WHERE SYMBOL = ? AND TYPE = ? AND EXPIRY_DATE = ? AND STRIKE_PRICE = ?";
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(FIND_SPOT_PRICE)){
			ps.setString(1, symbol);
			ps.setString(2, type);
			ps.setString(3, expiryDate);
			ps.setDouble(4, strikePrice);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				spotPrice = set.getDouble("LTP");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return spotPrice;
	}
}
