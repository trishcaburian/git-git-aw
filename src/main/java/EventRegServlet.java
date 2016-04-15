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
    	out.println("oks date and time :)");
		
		/*SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
		out.println("oks simpledateformat :)");
        Date dateparsed = format.parse(eDate);
		out.println("oks dateparsed :)");*/
        //java.sql.Date sql = new java.sql.Date(parsed.getTime());
		
		//format = new SimpleDateFormat("hh:mm aaa");		
		
		/*Time startTimeParsed = new Time(startTime);
		Time endTimeParsed = new Time(endTime);*/
		
    	

		PostgreSQLClient db = new PostgreSQLClient();
		//out.println("oks postgre instantiate :)");
		//	db.createEventTable();
			//out.println("oks createEventTable :)");
		//	db.addEvent(eName, eDesc, eDate, startTime, endTime, eventLocation);
			//out.println("oks addEvent :)");
		//	List<EventInfo> eventLoc = db.getAllEvents();
			//out.println("oks List EventInfo :)");
			/*
			for(int i =0;i<eventLoc.size();i++){
				out.println(eventLoc.get(i));
			}*/
			int ehID = db.getEID("sample");
			out.println(ehID);
			
		}catch(Exception e){
			out.println(e.getMessage());
		}

		//out.println(row);
		//out.println(total);
		
    }
}