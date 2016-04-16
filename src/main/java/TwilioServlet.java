/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package main;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yla
 */
@WebServlet(name = "TwilioServlet", urlPatterns = {"/TwilioServlet"})
public class TwilioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TwilioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TwilioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
		PrintWriter out = response.getWriter();
        Sms msg = null;
        TwilioConnect connect = new TwilioConnect();
		
        String authToken = connect.getAuthToken();
        String accountSID = connect.getAcctSID();
        TwilioRestClient tw_client = new TwilioRestClient(accountSID, authToken);
        
        Map<String, String> params = new HashMap<String, String>();
        
		String twilionum = "+12675441449";
		
		/*
		String[] voter = new String[3]; //maybe change it to a more dynamic array/list?
		voter[0] = "9274793339";
		voter[1] = "9179489196";
		voter[2] = "9334132146";
		*/
		
		List<String> numlist = request.getSession().getParameter("numbers");
		
		//for(int i = 0 ; i < voter.length ; i++){
		for(String numstring; numlist){
			params.put("From", twilionum);
			params.put("Body", request.getSession().getParameter("smsmsg"));
			//params.put("To", "+63"+voter[i]);
			params.put("To", "+63"+numstring);
			
			SmsFactory msgFactory = tw_client.getAccount().getSmsFactory();
			try {
				msg = msgFactory.create(params);
			}
			catch (TwilioRestException e) {
				throw new ServletException(e);
			}
		}
        out.println("Sent message id: " + msg.getSid());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        /*PrintWriter out = response.getWriter();
        Sms msg = null;
        TwilioConnect connect = new TwilioConnect();
		
        String authToken = connect.getAuthToken();
        String accountSID = connect.getAcctSID();
        TwilioRestClient tw_client = new TwilioRestClient(accountSID, authToken);
        
        Map<String, String> params = new HashMap<String, String>();
        
		String twilionum = "+12675441449";
		
		//list
		String[] voter = new String[3]; //maybe change it to a more dynamic array/list?
		voter[0] = "9274793339";
		voter[1] = "9179489196";
		voter[2] = "9334132146";
		
		List<String> numlist = request.getSession().getParameter("numbers");
		
		for(int i = 0 ; i < voter.length ; i++){
		//for(String numstring; numlist){
			params.put("From", twilionum);
			params.put("Body", request.getSession().getParameter("smsmsg"));
			params.put("To", "+63"+voter[i]);
			//params.put("To", "+63"+numstring);
			
			SmsFactory msgFactory = tw_client.getAccount().getSmsFactory();
			try {
				msg = msgFactory.create(params);
			}
			catch (TwilioRestException e) {
				throw new ServletException(e);
			}
		}
        out.println("Sent message id: " + msg.getSid());*/
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
