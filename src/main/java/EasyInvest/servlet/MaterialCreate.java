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


@WebServlet("/materialcreate")
public class MaterialCreate extends HttpServlet {
	
	protected EducationalMaterialsDao educationalMaterialsDao;
	
	@Override
	public void init() throws ServletException {
		educationalMaterialsDao = educationalMaterialsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/EducationalMaterialCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid user name");
        } 
        else {
        	String title = req.getParameter("title");
        	String content = req.getParameter("content");
        	Date created = new Date();// updated the date Yuhan Dec 07
        	//boolean published = Boolean.parseBoolean(req.getParameter("published"));
        	String username = req.getParameter("username");

        	try {
	        	EducationalMaterials educationamaterials = new EducationalMaterials(title, content, created, true, username);
	        	educationamaterials = educationalMaterialsDao.create(educationamaterials);
	        	messages.put("success", "Successfully created educational material");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/MaterialsCreate.jsp").forward(req, resp);
    }
}