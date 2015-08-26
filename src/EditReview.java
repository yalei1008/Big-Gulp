import java.io.*;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editreview")
public class EditReview extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DatabaseConnection dbc;
	String reviewID = "";
	
	public void init() throws ServletException {
		dbc = new DatabaseConnection("testdb", "password");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html");
		
		reviewID = request.getParameter("reviewID");
		fill(request, reviewID);
		
		String navRight = Utilities.checkLogin(dbc, request);
		request.setAttribute("navRight", navRight); 
		
		getServletContext().getRequestDispatcher("/Editreview.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("5");
		String rating  = request.getParameter("rating");
		System.out.println("6");
		String review  = request.getParameter("review");

		System.out.println("7");
		System.out.println(rating);
		System.out.println(review);
		
		ResultSet rs = dbc.query(
				  " UPDATE reviews"
				+ " set rating="+rating+", review='"+review+"'"
				+ " where id="+reviewID
		);

		System.out.println(rating);
		System.out.println(review);
				
		getServletContext().getRequestDispatcher("/Editreview.jsp").forward(request, response);
	}
	
	// Fill Form
	public void fill(HttpServletRequest request, String reviewID) {
		System.out.println("fill");
		ResultSet rs = dbc.query("SELECT * FROM reviews WHERE id = " + reviewID);
		System.out.println("SELECT * FROM reviews WHERE id = " + reviewID);
		try {
			if (rs.next()) {
				System.out.println( rs.getString("rating"));
				System.out.println( rs.getString("review"));
				request.setAttribute("rating", ratingIndex(rs.getString("rating")));
				request.setAttribute("review", rs.getString("review"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int ratingIndex(String rating) {
		if (rating.equals("5")) { return 0; }
		if (rating.equals("4")) { return 1; }
		if (rating.equals("3")) { return 2; }
		if (rating.equals("2")) { return 3; }
		if (rating.equals("1")) { return 4; }
		return 2;
	}
	
	public void destroy() { 
		dbc.disconnect();
	}
}

