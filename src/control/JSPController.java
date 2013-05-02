package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854143742430191264L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String address = "";

		if (request.getParameter("mediumConfirm") != null) {
			
			address = "/medium_confirmation.jsp";
		} else if (request.getParameter("typeConfirm") != null) {
			
			address = "/type_confirmation.jsp";
		} else if (request.getParameter("albumConfirm") != null) {
			
			address = "/album_confirmation.jsp";
		} else if (request.getParameter("mediumNext") != null) {

			address = "/medium_processing.jsp";
		} else if (request.getParameter("typeNext") != null) {

			address = "/type_processing.jsp";
		} else if (request.getParameter("albumNext") != null) {

			address = "/album_processing.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
