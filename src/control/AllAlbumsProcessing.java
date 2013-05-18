package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AllAlbumsProcessing extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "/allAlbums.jsp";
		
		request.setAttribute("albums", sqlController.getAllAlbums());
		redirect = "/allAlbums.jsp";

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirect = "";
		
		if (req.getParameter("details")!= null){
			redirect = "albumDetails.jsp";
			
	        Integer id = new Integer(req.getParameter("id"));	 
	        req.setAttribute("album", sqlController.getObjectById("model.Album", id));
		}
		else if (req.getParameter("buy")!= null){
			//TODO buy the album
		}
		else if (req.getParameter("play")!= null){
			//TODO play the album
		}
		else if (req.getParameter("newType")!= null){
			redirect = "new_type.jsp";
		}
		else if (req.getParameter("newMedium")!= null){
			redirect = "new_medium.jsp";
		}
		else if (req.getParameter("newAlbum")!= null){
			redirect = "new_album.jsp";
		}
		else if (req.getParameter("shoppingBasket")!= null){
			//TODO open shopping basket
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);       
	} 
	
}
