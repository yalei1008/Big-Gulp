import java.io.*;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/editrest")
public class EditRest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DatabaseConnection dbc;
	String restID ="";
	
	public void init() throws ServletException {
		dbc = new DatabaseConnection("testdb", "password");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html");
		restID = request.getParameter("restID");
		fill(request, restID);
		
		String navRight = Utilities.checkLogin(dbc, request);
		request.setAttribute("navRight", navRight); 
		
		getServletContext().getRequestDispatcher("/Editrest.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name  = request.getParameter("name");
		String address  = request.getParameter("address");
		String description  = request.getParameter("description");
		
		ResultSet rs = dbc.query(
			 " UPDATE restaurants"
			+" SET name = '"+name+"', address='"+address+"', description='"+description+"'"
			+" where id="+restID
		);
				
		getServletContext().getRequestDispatcher("/Editrest.jsp").forward(request, response);
	
	
	}
	
	// Fill Form
	public void fill(HttpServletRequest request, String restID) {
		ResultSet rs = dbc.query("SELECT * FROM restaurants WHERE id = " + restID);
		try {
			if (rs.next()) {
				request.setAttribute("name",          rs.getString("name"));
				request.setAttribute("address", rs.getString("address"));
				request.setAttribute("description"  , rs.getString("description"));

			}
		} catch (Exception e) {
			
		}
	}
	
	public void destroy() { 
		dbc.disconnect();
	}
}




