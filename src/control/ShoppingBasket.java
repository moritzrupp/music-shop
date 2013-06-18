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


public class ShoppingBasket extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SQLController sqlController = new SQLController();

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirect = "";
		
		if (req.getParameter("details")!= null){
			redirect = "mediumDetails.jsp";
			
	        Integer id = new Integer(req.getParameter("id"));	 
	        req.setAttribute("medium", sqlController.getObjectById("model.Medium", id));
		}
		else if (req.getParameter("delete")!= null){
			Set<Medium> set = (TreeSet<Medium>)req.getSession().getAttribute("shoppingBasket");
			Medium deleteObject=null;
			for (Medium med : set)
				if (med.getId() == Integer.parseInt(req.getParameter("id")))
					deleteObject = med;
			set.remove(deleteObject);
			redirect = "shoppingBasket.jsp";
		}
		else if (req.getParameter("play")!= null){
			
			redirect = "player.jsp";
			
			Medium m = (Medium)sqlController.getObjectById("model.Medium", Integer.parseInt(req.getParameter("id")));
	        req.setAttribute("medium", m);
	        
	        m.setListened(m.getListened()+1);
	        sqlController.saveObject(m);
		}
		else if (req.getParameter("clear")!= null){
			req.getSession().setAttribute("shoppingBasket", new TreeSet<Medium>());
			redirect = "shoppingBasket.jsp";			
		}
		else if (req.getParameter("print")!= null){
			Set<Medium> set = (TreeSet<Medium>)req.getSession().getAttribute("shoppingBasket");
			req.getSession().setAttribute("numbers", set.size());
				
			for (Medium med : set){
				med.setSold(med.getSold()+1);
				sqlController.saveObject(med);
			}
			req.getSession().setAttribute("shoppingBasket", new TreeSet<Medium>());
			redirect = "print.jsp";
		}
		else if (req.getParameter("back")!= null){
			redirect = "AllMediaProcessing";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);       
	} 
	
}
