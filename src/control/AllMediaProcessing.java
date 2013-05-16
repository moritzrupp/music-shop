package control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;

import model.Medium;


public class AllMediaProcessing extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("blub");
		String redirect = "/allMedia.jsp";
		
		request.setAttribute("media", sqlController.getAllMedia());
		redirect = "/allMedia.jsp";

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirect = "new_type.jsp";
		
		if (req.getParameter("details")!= null){
			redirect = "mediumDetails.jsp";
			
	        Integer id = new Integer(req.getParameter("id"));	 
	        req.setAttribute("medium", sqlController.getMedium(id));
		}
		else if (req.getParameter("buy")!= null){
			//TODO buy the medium
		}
		else if (req.getParameter("play")!= null){
			//TODO play the medium
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
		System.out.println(redirect);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);       
	} 
	
}
