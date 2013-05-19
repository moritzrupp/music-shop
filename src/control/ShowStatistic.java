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
		int num = (req.getParameter("num") != null) ? Integer.parseInt(req.getParameter("num")) : 5;
		int limit = (req.getParameter("limit") != null) ? Integer.parseInt(req.getParameter("limit")) : 5;
		
		List<Medium> topPlayed = sqlController.getTopPlayedMedia(num);
		List<Medium> topBought = sqlController.getTopBoughtMedia(limit);
		
		req.getSession().setAttribute("topPlayed", topPlayed);
		req.getSession().setAttribute("topBought", topBought);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
		dispatcher.forward(req, resp);
	}
}
