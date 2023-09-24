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


@WebServlet("/userstrategyposts")
public class UserStrategyPosts extends HttpServlet {
	
	protected StrategyPostDao strategyPostDao;
	
	@Override
	public void init() throws ServletException {
		strategyPostDao = strategyPostDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "StrategyPosts for " + userName);
        }
     // Retrieve BlogUsers, and store in the request.
        List<StrategyPost> strategyPost = new ArrayList<StrategyPost>();
        try {
        	strategyPost = strategyPostDao.getStrategyPostsByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("strategyPost", strategyPost);
        req.getRequestDispatcher("/UserStrategyPosts.jsp").forward(req, resp);
	}
}
