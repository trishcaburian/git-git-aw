import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

@WebServlet(name = "EventRegServlet", urlPatterns = {"/EventRegServlet"})
public class EventRegServlet extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
		try{
			String eName = request.getParameter("eventName");
	    	String eDesc = request.getParameter("eventDesc");
			String eventLocation = request.getParameter("eventLocation");
			
	    	String eDate = request.getParameter("datepicker").toString();
			String startTime = request.getParameter("startTime").toString();
	    	String endTime = request.getParameter("endTime").toString();
		

			PostgreSQLClient db = new PostgreSQLClient();

			db.addEvent(eName, eDesc, eDate, startTime, endTime, eventLocation);
			out.println("oks addEvent :)");

			int ehID = db.getEID(eName);
			out.println(ehID);
			int locID = db.getLocID(eventLocation);
			out.println(locID);
			db.addEvent_loc(ehID, locID);
			out.println("yey!");

			List<String> mob = db.getMobile(ehID);
        	out.println("result:");
        	for(String s: mob)
        		out.println(s);
				
			}catch(Exception e){
				out.println(e.getMessage());
			}
		
    }
}