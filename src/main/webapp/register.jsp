<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration</title>
</head>

<body>

	<h4>COMELEC Registration Form</h4>
	<form method="POST" action="UserRegServlet">
		Last name: <input type="text" name="lname"/> <br/>
		First name: <input type="text" name="fname"/> <br/>
		Address: <br/>
		Province: <input type="text" name="province"/> <br/>
		City/Municipality: <input type="text" name="city"/> <br/>
		Barangay: <input type="text" name="barangay"/> <br/>
		House No./Street <input type="text" name="street"/> <br/>
		Mobile: <input type="text" name="mobile"/> <br/>

		<input type="submit" value="Register"/>
	</form>

</body>

</html>
