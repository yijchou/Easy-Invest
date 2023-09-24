package EasyInvest.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import EasyInvest.dal.*;
import EasyInvest.model.*;



@WebServlet("/getdaily")
public class GetDaily extends HttpServlet{
	protected StocksDao stocksDao;
	protected DailyStockRecommendationDao dailyDao;
	
	@Override
	public void init() throws ServletException {
		stocksDao = StocksDao.getInstance();
		dailyDao = DailyStockRecommendationDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<String> tickers = new ArrayList<String>();
        try {
			tickers = stocksDao.getDistinctTickerNames();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        List<DailyStockRecommendation> recomByTicker = new ArrayList<DailyStockRecommendation>();
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String givenTicker = req.getParameter("givenTicker");
        if (givenTicker == null || givenTicker.trim().isEmpty() || tickers.contains(givenTicker)==false) {
            messages.put("success", "Please enter a valid ticker.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		recomByTicker = dailyDao.getRecommendationByTicker(givenTicker);
            	
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + givenTicker);
        }
        req.setAttribute("recTicker", recomByTicker);
        req.getRequestDispatcher("/GetDaily.jsp").forward(req, resp);
	}
	
}