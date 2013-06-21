package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Medium;

public class ShowStatistic extends HttpServlet {

	private static final long serialVersionUID = -1273960608816884823L;
	private SQLController sqlController = new SQLController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String redirect = "statistic.jsp";
		
		if(req.getSession().getAttribute("num") == null) {
			
			req.getSession().setAttribute("num", (req.getParameter("num") != null) ? Integer.parseInt(req.getParameter("num")) : 5);
		}
		
		if(req.getSession().getAttribute("limit") == null) {
			
			req.getSession().setAttribute("limit", (req.getParameter("limit") != null) ? Integer.parseInt(req.getParameter("limit")) : 5);
		}
			
		if(req.getParameter("num") != null) {
			
			req.getSession().setAttribute("num", Integer.parseInt(req.getParameter("num")));
		}
		
		if(req.getParameter("limit") != null) {
			
			req.getSession().setAttribute("limit", Integer.parseInt(req.getParameter("limit")));
		}
		
		List<Medium> topPlayed = sqlController.getTopPlayedMedia(0, (int)req.getSession().getAttribute("num"));
		List<Medium> topBought = sqlController.getTopBoughtMedia(0, (int)req.getSession().getAttribute("limit"));
		
		req.getSession().setAttribute("topPlayed", topPlayed);
		req.getSession().setAttribute("topBought", topBought);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);
	}
}
