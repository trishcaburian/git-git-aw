<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Message Send</title>
</head>

<body>

    <h4>Message</h4>

    <% if (request.getSession().getAttribute("smsmsg") != null) { %>
        <div><%= request.getSession().getAttribute("smsmsg") %></div>
    <% } %>
    <br/>
	
	<form method="POST" action="/TwilioServlet">
	
		<input type="text" name="msg" value="<%=request.getSession().getAttribute("smsmsg")%>"/>
		<input type="text" name="eid" value="<%=request.getSession().getAttribute("numbers")%>"/>
		
		<input type="submit" value="send"/>
	
	</form>
    <a href="/TwilioServlet">Send Message!</a>
</body>

</html>