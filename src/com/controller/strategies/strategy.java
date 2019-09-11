package com.controller.strategies;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.StrategyLogic;
import com.pojo.Derivative;
import com.pojo.Strategy;
import com.pojo.StrategyOutput;

/**
 * Servlet implementation class strategy
 */
@WebServlet("/strategy")
public class strategy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public strategy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String symbol = "HDFCBANK";
		String views = "Neutral";
		String expiry_date = "26-SEP-19";
		double target = 2180;
		
		List<Strategy> strategies = new ArrayList<>();
		StrategyLogic s = new StrategyLogic();
		strategies = s.getStrategy(symbol,views,expiry_date,target);
			
		List<StrategyOutput> l = new ArrayList<>();
		
		for(int i=0;i<strategies.size();i++)
		{
			
			switch(strategies.get(i).getStrategyName())
			{
			case "Bull Call Spread":
				l = s.BullCallSpread(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Long Call Butterfly":
				l = s.LongCallButterfly(symbol, target, expiry_date);
				System.out.println(l);
				break;
				
			case "Bear Put Spread":
				l = s.BearPutSpread(symbol, target, expiry_date);
				System.out.println(l);
				break;
			}
		}
		
		for(int i=0;i<l.size(); i++)
		{
			System.out.println(l.get(i));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
