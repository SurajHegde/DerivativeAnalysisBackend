package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MyConnection;
import com.pojo.Derivative;
import com.pojo.Holding;
import com.pojo.Strategy;

public class StrategyDAOImpl {

	public List<Strategy> getStrategies(String views)
	{
		List<Strategy> strategies = new ArrayList();
		
		String query = "select * from strategies where views = ?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(query);){
			ps.setString(1, views);
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{
				System.out.println("inside set.next");
				Strategy s = new Strategy();
				s.setStrategyName(set.getString("strategyname"));
				s.setViews(views);
				System.out.println(s);
				strategies.add(s);
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return strategies;
	}
	
	public List<Holding> getHolding(String symbol, String type, String expiry_date)
	{
		List<Holding> holdings = new ArrayList();
		System.out.println(symbol+type+expiry_date);
		String query = "select * from derivatives where symbol=? and type=? and expiry_date=?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(query);){
			ps.setString(1, symbol);
			ps.setString(2, type);
			ps.setString(3, expiry_date);
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{
				Holding d = new Holding();
				d.setStrikePrice(set.getDouble("strike_price"));
				d.setLotSize(set.getInt("lot_size"));
				d.setLtp(set.getDouble("ltp"));
				d.setNumLots(1);
				d.setPremium(set.getDouble("premium"));
				d.setUnderlyingValue(set.getDouble("underlying_value"));
				
				holdings.add(d);
				
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return holdings;
	}
}
