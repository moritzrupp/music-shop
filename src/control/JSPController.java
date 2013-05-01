package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JSPController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854143742430191264L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String address="type_confirmation.jsp";

		if (request.getParameter("mediumConfirm") != null)
		{
		address = "medium_confirmation.jsp";
		}
		else if (request.getParameter("typeConfirm") != null)
		{
		address = "type_confirmation.jsp";
		}
		else if (request.getParameter("albumConfirm") != null)
		{
		address = "album_confirmation.jsp";
		}
		System.out.println(address);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
}
