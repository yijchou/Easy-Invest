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


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
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
        	String password = req.getParameter("password");
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String phone = req.getParameter("phone");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users users = new Users(userName, password, new Date(), false, firstName, lastName,  email, phone, Users.CompetencyLevel.BEGINNER);
	        	users = usersDao.create(users);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}
