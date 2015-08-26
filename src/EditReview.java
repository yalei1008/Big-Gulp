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
		
		System.out.println("1");
		reviewID = request.getParameter("reviewID");
		System.out.println("2 REVIEW ID: " + reviewID);
		String navRight = Utilities.checkLogin(dbc, request);
		System.out.println("3");
		request.setAttribute("navRight", navRight); 
		System.out.println("4");
		
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
	
	public void destroy() { 
		dbc.disconnect();
	}
}

