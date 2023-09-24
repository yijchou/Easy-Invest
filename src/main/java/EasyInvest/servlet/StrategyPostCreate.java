package EasyInvest.servlet;

import EasyInvest.dal.*;
import EasyInvest.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/strategypostcreate")
public class StrategyPostCreate extends HttpServlet {
	
	protected StrategyPostDao strategyPostDao;
	
	@Override
	public void init() throws ServletException {
		strategyPostDao = StrategyPostDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/StrategyPostCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	String title = req.getParameter("title");
        	String content = req.getParameter("content");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	StrategyPost strategyPost = new StrategyPost(title, content, new Date(), userName, true, 0);
	        	strategyPost = strategyPostDao.create(strategyPost);
	        	messages.put("success", "Post successfully created ");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/StrategyPostCreate.jsp").forward(req, resp);
    }
}