package com.controller.logic;
import com.logic.DerivativeLogic;
import com.logic.Pair;
import com.pojo.Holding;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GeneratePayoffServlet
 */
@WebServlet("/GeneratePayoffServlet")
public class GeneratePayoffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePayoffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		List<Holding> holdingList = (List<Holding>) httpSession.getAttribute("holdingList");
		DerivativeLogic dl = new DerivativeLogic();
		List<Pair>coordinateList = dl.generatePayoff(holdingList);
		request.setAttribute("coordinateList",coordinateList);
//		for(Pair p: coordinateList) {
//			System.out.println(p.getX() + " " + p.getY());
//		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("generatePayoff.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
