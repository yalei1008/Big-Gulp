import java.io.*;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/edituser")
public class EditUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DatabaseConnection dbc;
	String userID = "";
	
	public void init() throws ServletException {
		dbc = new DatabaseConnection("testdb", "password");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html");
		userID = request.getParameter("userID");
		fill(request, userID);
		String navRight = Utilities.checkLogin(dbc, request);
		request.setAttribute("navRight", navRight); 
		
		getServletContext().getRequestDispatcher("/Edituser.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name  = request.getParameter("name");
		String zip  = request.getParameter("zip");
		String email  = request.getParameter("email");
		
		System.out.println(				  " UPDATE users"
				+ " SET name='" + name+ "', zip= " + zip + ", email='" + email + "'"
				+ " where id="+ userID);
		ResultSet rs = dbc.query(
				  " UPDATE users"
				+ " SET name='" + name+ "', zip= " + zip + ", email='" + email + "'"
				+ " where id="+ userID	
		);

				
		getServletContext().getRequestDispatcher("/Edituser.jsp").forward(request, response);
	
	
	}
	
	// Fill Form
	public void fill(HttpServletRequest request, String userID) {
		ResultSet rs = dbc.query("SELECT * FROM users WHERE id = " + userID);
		try {
			if (rs.next()) {
				request.setAttribute("name",          rs.getString("name"));
				request.setAttribute("zip", rs.getString("zip"));
				request.setAttribute("email"  , rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() { 
		dbc.disconnect();
	}
}


