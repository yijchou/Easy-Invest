package EasyInvest.servlet;
import java.io.IOException;
import java.sql.SQLException;
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



@WebServlet("/findStocks")
public class FindStocks extends HttpServlet{
	protected StocksDao stocksDao;
	
	@Override
	public void init() throws ServletException {
		stocksDao = StocksDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        String messages = "Successfully obtained all tickers!";
        req.setAttribute("messages", messages);

        List<String> tickers = new ArrayList<String>();
        
    	try {
        	tickers = stocksDao.getDistinctTickerNames();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.setAttribute("tickers", tickers);
        
        req.getRequestDispatcher("/FindStocks.jsp").forward(req, resp);
    }
	
}