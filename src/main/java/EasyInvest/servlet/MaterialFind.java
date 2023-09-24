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


@WebServlet("/materialfind")
public class MaterialFind extends HttpServlet {
	
	protected EducationalMaterialsDao educationalMaterialsDao;
	
	@Override
	public void init() throws ServletException {
		educationalMaterialsDao = EducationalMaterialsDao.getInstance();
	}
	
/*Do Get*/
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<EducationalMaterials> educationalMaterials = new ArrayList<EducationalMaterials>();
        
        // The username of the admin is set as Beginner, Intermediate, Advanced, Expert -- updated by Yuhan Dec 06
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid competency level of tutorial.");
        } else {
        	try {
        		//get materials from EducationalMaterialsDao by username -- the admin name which is the competency level
        		educationalMaterials = educationalMaterialsDao.getEducationalMaterialsByUserName(userName);
        		if (!educationalMaterials.contains(userName)) {
    				messages.put("success", "Materials with this competecy level is not found.");
    			} else {
            	messages.put("success", "Displaying results for materials with competency level: " + userName);
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("previousMaterialsCompetencyLevel", userName);
        }
        req.setAttribute("educationalMaterials", educationalMaterials);
        
        req.getRequestDispatcher("/MaterialsFind.jsp").forward(req, resp);
	}

/*Do Post*/
//@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//    		throws ServletException, IOException {
//        
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        List<Users> users = new ArrayList<Users>();
//
//        String firstName = req.getParameter("firstname");
//        if (firstName == null || firstName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//        	// Retrieve membership users, and store as a message.
//        	try {
//        		users = usersDao.getUsersFromFirstName(firstName);
//        				
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + firstName);
//        }
//        req.setAttribute("users", users);
//        
//        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
//    }
}