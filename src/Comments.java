import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/comments")
public class Comments extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DatabaseConnection dbc;
	String userID = "";
	String reviewID = "";
	
	public void init() throws ServletException {
		dbc = new DatabaseConnection("testdb", "password");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html");
		userID = (String) request.getSession().getAttribute("userID");
		reviewID = request.getParameter("reviewID");
	
		String navRight = Utilities.checkLogin(dbc, request);
		request.setAttribute("navRight", navRight); 
		
		System.out.println("userID: "   + userID);
		System.out.println("reviewID: " + reviewID);
		
		getServletContext().getRequestDispatcher("/comments.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String comment  = request.getParameter("comment");
		
		System.out.println("Comment: "  + comment);
		System.out.println("userID: "   + userID);
		System.out.println("reviewID: " + reviewID);
		
		int maxID = 0;
		ResultSet rs = dbc.query("select max(id) from comments");
		try {
			if(rs.next()) {
				maxID = Integer.parseInt(rs.getString("max(id)"));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(maxID);
		
		rs = dbc.query(
			  " INSERT INTO comments(id,review_id,user_id,ct)"
			+ " VALUES ("+(++maxID)+","+reviewID+","+userID+",'"+comment+"')"
		);
	
		getServletContext().getRequestDispatcher("/comments.jsp").forward(request, response);
	}
	

	public void destroy() { 
		dbc.disconnect();
	}
}
