<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BloodIndia</title>
</head>
<body>
<form action="../rest/donorSearch" method="post">
<select  placeholder="Blood Group" id="bloodGroup" value="">
<option>A+</option>
<option>A-</option>
<option>B+</option>
<option>B-</option>
<option>AB+</option>
<option>AB-</option>
<option>O+</option>
<option>O-</option>
<option>BOMBAY</option>
</select>
<br/>
<input type="text" placeholder="Locality" id="locality" value=""/><br/>
<input type="text" placeholder="city" id="city" value=""/><br/>
<button type="submit">submit</button>
</form>

</body>
</html>