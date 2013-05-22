package control;

import java.io.IOException;
import java.util.TreeSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Medium;


public class AllMediaProcessing extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "/allMedia.jsp";
		
		request.setAttribute("media", sqlController.getAllMedia());
		redirect = "/allMedia.jsp";

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirect = "";
		
		if (req.getParameter("details")!= null){
			redirect = "mediumDetails.jsp";
			
	        Integer id = new Integer(req.getParameter("id"));	 
	        req.setAttribute("medium", sqlController.getObjectById("model.Medium", id));
		}
		else if (req.getParameter("buy")!= null){
			
			if (req.getSession().getAttribute("shoppingBasket") == null)
				req.getSession().setAttribute("shoppingBasket", new TreeSet<Medium>());
			@SuppressWarnings("unchecked")
			Set<Medium> set = (TreeSet<Medium>)req.getSession().getAttribute("shoppingBasket");
			set.add((Medium)sqlController.getObjectById("model.Medium", new Integer(req.getParameter("id"))));
			req.getSession().setAttribute("shoppingBasket", set);
			this.doGet(req, resp);
			return;
		}
		else if (req.getParameter("play")!= null){
			
			redirect = "player.jsp";
			
			Medium m = (Medium)sqlController.getObjectById("model.Medium", Integer.parseInt(req.getParameter("id")));
	        req.setAttribute("medium", m);
	        
	        m.setListened(m.getListened()+1);
	        sqlController.saveObject(m);
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
			redirect = "shoppingBasket.jsp";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);       
	} 
	
}
