import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "UserRegServlet", urlPatterns = {"/UserRegServlet"})
public class UserRegServlet extends HttpServlet 
{
	/*private CloudantClientClass db = new CloudantClientClass();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	JSONObject obj = new JSONObject();

    	PrintWriter out = response.getWriter();

    	obj.put("lname", request.getParameter("lname"));
    	obj.put("fname", request.getParameter("fname"));
    	obj.put("province", request.getParameter("province"));
    	obj.put("city", request.getParameter("city"));
    	obj.put("barangay", request.getParameter("barangay"));
    	obj.put("street", request.getParameter("street"));
    	obj.put("mobile", request.getParameter("mobile"));

    	if(request.getParameter("notif").equals("location")){

    		JSONArray arr = new JSONArray();
    		String[] loc = request.getParameterValues("loc");

    		for(String s: loc)
    			arr.add(s);

    		obj.put("locations", arr);
    	}

    	out.println(obj.toString());

    	try{
    		out.println(db.addEntry(obj));
    	}catch(Exception e){
    		out.println(e.getMessage());
    	}
    	
    	

    	/*
		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        */
    }
}