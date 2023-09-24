package EasyInvest.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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



@WebServlet("/getdailydate")
public class GetDailyDate extends HttpServlet{
	protected DailyStockRecommendationDao dailyDao;
	
	@Override
	public void init() throws ServletException {
		dailyDao = DailyStockRecommendationDao.getInstance();
	}
	
	public boolean isValidateDateFormat(String dateToValdate) {

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    //To make strict date format validation
	    formatter.setLenient(false);
	    Date parsedDate = null;
	    try {
	        parsedDate = formatter.parse(dateToValdate);

	    } catch (ParseException e) {
	        //Handle exception
	    }
	    if (parsedDate == null) {
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String givenDate = req.getParameter("givenDate");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<DailyStockRecommendation> recomByDate = new ArrayList<DailyStockRecommendation>();
        // Retrieve and validate name.
        
        
        if (givenDate == null || givenDate.trim().isEmpty() || !isValidateDateFormat(givenDate)) {
            messages.put("success", "Please enter a valid Date in format yyyy-MM-dd 00:00:00.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		Date date = formatter.parse(givenDate); ;
				recomByDate = dailyDao.getRecommendationByDate(date);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	messages.put("success", "Displaying results");
        }
        req.setAttribute("recDate", recomByDate);
        req.getRequestDispatcher("/GetDailyDate.jsp").forward(req, resp);
	}
	
}