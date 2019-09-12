package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.connection.MyConnection;
import com.logic.CalculatePLLogic;

import com.logic.Pair;
import com.logic.UserHoldingExtraFunctions;
import com.pojo.Holding;

public class DerivativeDAOImpl implements DerivativeDAO {

//	public List<Holding> getAllUserHoldings(String emailId) {
//		// TODO Auto-generated method stub
//		List<Holding> allUserHoldings = new ArrayList<Holding>();
//		String GET_HOLDINGS_USER = "select * from holdings where emailid = ?";
//		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_HOLDINGS_USER);){
//			ps.setString(1,emailId);
//			ResultSet set = ps.executeQuery();
//			while(set.next()) {
//				Holding userHolding = new Holding();
//				userHolding.setAvgPrice(set.getDouble("avg_price"));
//				userHolding.setSymbol(set.getString("symbol"));
//				userHolding.setType(set.getString("type"));
//				userHolding.setPosition(set.getString("position"));
//				userHolding.setExpiryDate(set.getString("expiry_date"));
//				userHolding.setNumLots(set.getInt("lots"));
//				userHolding.setPremium(set.getDouble("premium"));
//				allUserHoldings.add(userHolding);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return allUserHoldings;
//	}
	//Check if user has a particular holding
	@Override
	public Holding checkUserHolding(String emailId, String symbol, String type, String expiryDate,double strikePrice) {
		// TODO Auto-generated method stub
		Holding userHolding = new Holding();
		String CHECK_HOLDINGS = "select * from holdings where emailid = ? and type = ? and symbol = ? and expiry_date = ? and strike_price = ?";
		try (PreparedStatement ps = MyConnection.openConnection().prepareStatement(CHECK_HOLDINGS);){
			ps.setString(1, emailId);
			ps.setString(2, type);
			ps.setString(3, symbol);
			ps.setString(4, expiryDate);
			ps.setDouble(5, strikePrice);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				userHolding.setAvgPrice(set.getDouble("avg_price"));
				userHolding.setSymbol(set.getString("symbol"));
				userHolding.setType(set.getString("type"));
				userHolding.setPosition(set.getString("position"));
				userHolding.setExpiryDate(set.getString("expiry_date"));
				userHolding.setNumLots(set.getInt("lots"));
				userHolding.setPremium(set.getDouble("premium"));
				userHolding.setStrikePrice(set.getDouble("strike_price"));
				userHolding.setLtp(set.getDouble("lcp"));
				userHolding.setLotSize(set.getInt("lot_size"));
				return userHolding;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return userHolding;

	}
	//Add holdings if the user does not have them or else update
	@Override
	public boolean addUserHolding(String emailId,String type, String position, double strikePrice, String symbol,String expiryDate,double lotSize, double lcp,double premium,int numLots,double spotPrice) {
		// TODO Auto-generated method stub
		try(Connection conn = MyConnection.openConnection()) {
			Holding holdings = checkUserHolding(emailId, symbol, type, expiryDate, strikePrice);
			if(holdings.getSymbol() != "") {
				boolean rows = updateUserHolding(emailId, type, symbol, position, expiryDate, strikePrice, numLots, spotPrice,premium);
				return rows;
			} else {
				String ADD_HOLDINGS = "insert into holdings(emailid,symbol,type,position,expiry_date,strike_price,lot_size,lots,premium,lcp,avg_price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				PreparedStatement ps = conn.prepareStatement(ADD_HOLDINGS);
				ps.setString(1, emailId);
				ps.setString(2, symbol);
				ps.setString(3, type);
				ps.setString(4, position);
				ps.setString(5, expiryDate);
				ps.setDouble(6, strikePrice);
				ps.setDouble(7, lotSize);
				ps.setInt(8, numLots);
				lcp = getLCP(symbol, type, expiryDate, strikePrice);
				ps.setDouble(10, lcp);
				if(type.equals("FUT")) {
					ps.setDouble(11, spotPrice);
					ps.setDouble(9, 0);
				} else {
					ps.setDouble(9, premium);
					ps.setDouble(11, 0);
				}
				int rows = ps.executeUpdate();
				if(rows > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public int deleteHolding(String emailId,String symbol,String type,String expiryDate,double strikePrice) {
		int rows = 0;
		String DELETE_HOLDING = "delete from holdings where emailid = ? and symbol = ? and type = ? and expiry_date = ? and strike_price = ?";
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(DELETE_HOLDING);){
			ps.setString(1, emailId);
			ps.setString(2, symbol);
			ps.setString(3, type);
			ps.setString(4, expiryDate);
			ps.setDouble(5, strikePrice);
			rows = ps.executeUpdate();
			return rows;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	//Update user holding
	@Override
	public boolean updateUserHolding(String emailId, String type, String symbol, String position, String expiryDate,
			double strikePrice, int numLots, double spotPrice,double premium) {
		// TODO Auto-generated method stub
		try(Connection conn = MyConnection.openConnection()){
			Holding holdings = checkUserHolding(emailId, symbol, type, expiryDate, strikePrice);
			if(type.equals("FUT")) {
				String UPDATE_FUTURES = "update holdings set lots = ?,avg_price = ?,position = ? where emailid = ? and symbol = ? and type = ? and expiry_date = ?";
				PreparedStatement ps = conn.prepareStatement(UPDATE_FUTURES);
				double userAvgPrice = holdings.getAvgPrice();
				int userLots = holdings.getNumLots();
				String userPosition = holdings.getPosition();
				if(userPosition.equals(position)) {
					userAvgPrice = ((spotPrice * numLots) + (userAvgPrice * userLots)) / (userLots + numLots);
					userLots += numLots;
				} else {
					if(userLots - numLots != 0) {
						if((userLots - numLots) < 0) {
							userAvgPrice = spotPrice;
							if(userPosition.equals("LONG"))
								userPosition = "SHORT";
							else
								userPosition = "LONG";
						}
						userLots -= numLots;
					}else {
						int rows = deleteHolding(emailId, symbol, type, expiryDate, strikePrice);
						if (rows > 0)
							return true;
						else
							return false;
					}
				}
				ps.setDouble(1, Math.abs(userLots));
				ps.setDouble(2, userAvgPrice);
				ps.setString(3, userPosition);
				ps.setString(4, emailId);
				ps.setString(5, symbol);
				ps.setString(6, type);
				ps.setString(7, expiryDate);
				int rows = ps.executeUpdate();
				if(rows > 0)
					return true;
				else 
					return false;
			} else {
				String UPDATE_OPTIONS = "update holdings set lots = ?,premium = ?,position = ? where emailid = ? and symbol = ?  and type = ? and strike_price = ? and expiry_date = ?";
				PreparedStatement ps = conn.prepareStatement(UPDATE_OPTIONS);
				double userPremium = holdings.getPremium();
				int userLots = holdings.getNumLots();
				String userPosition = holdings.getPosition();
				if(userPosition.equals(position)) {
					userPremium = ((spotPrice * numLots) + (userPremium * userLots)) / (userLots + numLots);
					userLots += numLots;
				} else {
					if(userLots - numLots != 0) {
						if((userLots - numLots) < 0) {
							userPremium = premium;
							if(userPosition.equals("LONG"))
								userPosition = "SHORT";
							else
								userPosition = "LONG";
						}
						userLots -= numLots;
					}else {
						int rows = deleteHolding(emailId, symbol, type, expiryDate, strikePrice);
						if(rows > 0) 
							return true;
						else
							return false;
					}
				}
				ps.setDouble(1, Math.abs(userLots));
				ps.setDouble(2, userPremium);
				ps.setString(3, userPosition);
				ps.setString(4, emailId);
				ps.setString(5, symbol);
				ps.setString(6, type);
				ps.setDouble(7, strikePrice);
				ps.setString(8, expiryDate);
				int rows = ps.executeUpdate();
				if(rows > 0)
					return true;
				else 
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/*Function to get list of derivatives for particular symbol,example FUT,CE,PE for HDFC
	 * 
	 * */
	@Override
	public List<Holding> getSpecificDerivative(String symbol) {
		// TODO Auto-generated method stub
		List<Holding> specificDerivative = new ArrayList<Holding>();
		String GET_DERIVATIVE = "select * from derivatives where symbol = ?";
		System.out.println(symbol);
		
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_DERIVATIVE)){
			ps.setString(1, symbol);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				System.out.println("inside while");
				Holding holding = new Holding();
				holding.setType(set.getString("type"));
				holding.setExpiryDate(set.getString("expiry_date"));
				holding.setStrikePrice(set.getDouble("strike_price"));
				holding.setLtp(set.getDouble("ltp"));
				holding.setLotSize(set.getInt("lot_size"));
				holding.setUnderlyingValue(set.getDouble("underlying_value"));
				holding.setPremium(set.getDouble("premium"));
				specificDerivative.add(holding);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return specificDerivative;
	}
	public int getLotSize(String symbol) {
		String GET_LOT_SIZE = "select lot_size from derivatives where symbol = ?";
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_LOT_SIZE);){
			ps.setString(1, symbol);
			ResultSet set = ps.executeQuery();
			int lotSize = 0;
			if(set.next()) { 
				lotSize = set.getInt("lot_size");
			}
			return lotSize;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	public double getLCP(String symbol,String type,String expiryDate,double strikePrice) {
		double spotPrice = 0.0;
		String FIND_SPOT_PRICE = "SELECT LCP FROM DERIVATIVES WHERE SYMBOL = ? AND TYPE = ? AND EXPIRY_DATE = ? AND STRIKE_PRICE = ?";
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(FIND_SPOT_PRICE)){
			ps.setString(1, symbol);
			ps.setString(2, type);
			ps.setString(3, expiryDate);
			if(type.equals("FUT"))
				ps.setDouble(4, 0);
			else
				ps.setDouble(4, strikePrice);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				spotPrice = set.getDouble("lcp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return spotPrice;
	}
	
	public HashMap<String,Double> getValueUser(String emailId) {
		String GET_DERIVATIVE = "select * from holdings where emailid = ?";
		HashMap<String,Double> totalValue = new HashMap<String,Double>();
		UserHoldingExtraFunctions extra = new UserHoldingExtraFunctions();
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_DERIVATIVE);){
			ps.setString(1, emailId);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				String symbol = set.getString("symbol");
				String type = set.getString("type");
				String expiryDate = set.getString("expiry_date");
				double strikePrice = set.getDouble("strike_price");
				int numLots = set.getInt("lots");
				double lcp = set.getDouble("lcp");
				double ltp = extra.getSpotPrice(symbol, type, expiryDate, strikePrice);
				int lotSize = getLotSize(symbol);
				if(totalValue.containsKey(symbol)) {
					if(type.equals("FUT")) {
						totalValue.put(symbol,totalValue.get(symbol) + (ltp-lcp)*numLots*lotSize);
					} else {
						double premium = set.getDouble("premium");
						totalValue.put(symbol,totalValue.get(symbol) + (premium-lcp)*numLots*lotSize);
					}
				} else {
					if(type.equals("FUT")){
						totalValue.put(symbol,(ltp-lcp)*numLots*lotSize);
					} else {
						double premium = set.getDouble("premium");
						totalValue.put(symbol,(premium-lcp)*numLots*lotSize);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return totalValue;
	}
	public HashMap<String,Double> getValueInstrument(String emailId,String symbol){
		HashMap<String,Double> instrumentValue = new HashMap<String,Double>();
		String GET_INSTRUMENTS = "select * from holdings where emailid = ? and symbol = ?";
		UserHoldingExtraFunctions extra = new UserHoldingExtraFunctions();
		try(PreparedStatement ps = MyConnection.openConnection().prepareStatement(GET_INSTRUMENTS)){
			ps.setString(1, emailId);
			ps.setString(2, symbol);
			int lotSize = getLotSize(symbol);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				String type = set.getString("type");
				String expiryDate = set.getString("expiryDate");
				double strikePrice = set.getDouble("strike_price");
				double lcp = set.getDouble("lcp");
				double ltp = extra.getSpotPrice(symbol, type, expiryDate, strikePrice);
				int numLots = set.getInt("lots");
				if(type.equals("FUT")) {
					String instrument = symbol + " " + type + " " + expiryDate;
					if(instrumentValue.containsKey(instrument)) {
						instrumentValue.put(instrument,instrumentValue.get(instrument) + (ltp-lcp)*numLots*lotSize);
					}else {
						instrumentValue.put(instrument,(ltp-lcp)*numLots*lotSize);
					}
				} else {
					String instrument = symbol + " " + expiryDate + " " + String.valueOf(strikePrice) + " " + type;
					double premium = set.getDouble("premium");
					if(instrumentValue.containsKey(instrument)) {
						instrumentValue.put(instrument, instrumentValue.get(instrument) + (premium-lcp)*lotSize*numLots);
					} else {
						instrumentValue.put(instrument,(premium-lcp)*numLots*lotSize);
					}
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return instrumentValue;
	}
}

