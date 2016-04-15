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

		<br/>
		Get Notification: <br/>
		<input type="radio" name="notif" value="all"/>All
		</br/><br/>
		<input type="radio" name="notif" value="location"/>Selected Locations:<br/>
		<h5>National Capital Region</h5>
		<input type="checkbox" name="loc" value="caloocan"/>Caloocan<br/>
		<input type="checkbox" name="loc" value="makati"/>Makati<br/>
		<input type="checkbox" name="loc" value="malabon"/>Malabon<br/>
		<input type="checkbox" name="loc" value="mandaluyong"/>Mandaluyong<br/>
		<input type="checkbox" name="loc" value="manila"/>Manila<br/>
		<input type="checkbox" name="loc" value="marikina"/>Marikina<br/>
		<input type="checkbox" name="loc" value="muntinlupa"/>Muntinlupa<br/>
		<input type="checkbox" name="loc" value="navotas"/>Navotas<br/>
		<input type="checkbox" name="loc" value="paranaque"/>Paranaque<br/>
		<input type="checkbox" name="loc" value="pasay"/>Pasay<br/>
		<input type="checkbox" name="loc" value="pasig"/>Pasig<br/>
		<input type="checkbox" name="loc" value="pateros"/>Pateros<br/>
		<input type="checkbox" name="loc" value="quezon"/>Quezon<br/>
		<input type="checkbox" name="loc" value="san juan"/>San Juan<br/>
		<input type="checkbox" name="loc" value="taguig"/>Taguig<br/>
		<input type="checkbox" name="loc" value="valenzuela"/>Valenzuela<br/>
		<br/>
		<input type="submit" value="Submit"/>
	</form>
		</body>

</html>
