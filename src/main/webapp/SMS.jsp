<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.InputStream, main.*"%>
<%@page import="com.twilio.sdk.TwilioRestClient"%>
<%@page import="com.twilio.sdk.TwilioRestException"%> 
<%@page import="com.twilio.sdk.resource.factory.SmsFactory%> 
<%@page import="com.twilio.sdk.resource.instance.Sms%> 
<%@page import="java.util.HashMap%>
<%@page import="java.util.Map"%>
<% 
		Sms msg = null;
        TwilioConnect connect = new TwilioConnect();
		
        String authToken = connect.getAuthToken();
        String accountSID = connect.getAcctSID();
        TwilioRestClient tw_client = new TwilioRestClient(accountSID, authToken);
        
        Map<String, String> params = new HashMap<String, String>();
        
        params.put("From", request.getParameter("twilio_num"));
        params.put("Body", request.getParameter("smsmsg"));
        params.put("To", request.getParameter("sendto"));
        
        SmsFactory msgFactory = tw_client.getAccount().getSmsFactory();
		try {
            msg = msgFactory.create(params);
        }
        catch (TwilioRestException e) {
            throw new ServletException(e);
		}
		out.println("Sent message id: " + msg.getSid());
%>