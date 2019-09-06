<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
<form action="CreateAccountServlet" method="post">
 E-MAIL:<input type="text" name="emailId"><br>
 FIRST NAME:<input type="text" name="firstName"><br>
 LAST NAME:<input type="text" name="lastName"><br>
 PASSWORD:<input type="password" name="password"><br>
 CONFIRM PASSWORD:<input type="password" name="confirmPassword"><br>

<input type="submit" name="Create account"> 
</form>
</body>
</html>