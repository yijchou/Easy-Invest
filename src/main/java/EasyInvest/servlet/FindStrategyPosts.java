package EasyInvest.servlet;

import EasyInvest.dal.*;
import EasyInvest.model.*;

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


@WebServlet("/findstrategyposts")
public class FindStrategyPosts extends HttpServlet {
	
	protected StrategyPostDao strategyPostDao;
	
	@Override
	public void init() throws ServletException {
		strategyPostDao = StrategyPostDao.getInstance();
	}
	
/*Do Get*/
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<StrategyPost> strategyPosts = new ArrayList<StrategyPost>();
        

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid user name.");
        } else {
        	try {
        		//get post from strategyPostDao by user name
        		strategyPosts = strategyPostDao.getStrategyPostsByUserName(userName);
        		if (strategyPosts.size() == 0) {
    				messages.put("success", "Strategy post with this username is not found.");
    			} else {
            	messages.put("success", "Displaying results for strategy posts with username: " + userName);
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("strategyPosts", strategyPosts);
        
        req.getRequestDispatcher("/FindStrategyPosts.jsp").forward(req, resp);
	}
}