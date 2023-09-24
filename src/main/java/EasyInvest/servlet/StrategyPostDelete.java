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


@WebServlet("/strategypostdelete")
public class StrategyPostDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete post");        
        req.getRequestDispatcher("/StrategyPostDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String postId = req.getParameter("postId");
        if (postId == null || postId.trim().isEmpty()) {
            messages.put("title", "Invalid post id");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        StrategyPost strategyPost = new StrategyPost(Integer.parseInt(postId));
	        try {
	        	strategyPost = strategyPostDao.delete(strategyPost);
	        	if (strategyPost == null) {
	        		messages.put("title", "Successfully deleted post with post ID: " + postId);
		            messages.put("disableSubmit", "true");
	        	} else {
	        	messages.put("title", "Failed to delete post with post ID: " + postId);
				messages.put("disableSubmit", "false");
	        	}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/StrategyPostDelete.jsp").forward(req, resp);
    }
}
