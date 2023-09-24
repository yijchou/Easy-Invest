package EasyInvest.servlet;

import EasyInvest.dal.*;
import EasyInvest.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/strategypostupdate")
public class StrategyPostUpdate extends HttpServlet {
	
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

        String postId = req.getParameter("postId");
        if (postId == null || postId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid post id.");
        } else {
        	try {
        		StrategyPost strategyPost = strategyPostDao.getStrategyPostById(Integer.parseInt(postId));
        		req.setAttribute("StrategyPost", strategyPost);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/StrategyPostUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String postId = req.getParameter("postId");
        if (postId == null || postId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid post id.");
        } else {
        	try {
        		StrategyPost strategyPost = strategyPostDao.getStrategyPostById(Integer.parseInt(postId));
        		String newContent = req.getParameter("content");
				if (newContent == null || newContent.trim().isEmpty()) {
				    messages.put("success", "Please enter a valid content.");
				} else {
					strategyPost = strategyPostDao.updateContent(strategyPost, newContent);
					messages.put("success", "Content successfully updated " + postId);
				}
        		req.setAttribute("strategyPost", strategyPost);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/StrategyPostUpdate.jsp").forward(req, resp);
    }
}
