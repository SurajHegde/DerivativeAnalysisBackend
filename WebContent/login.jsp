<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="LoginServlet" method="post">
 E-MAIL:<input type="text" name="emailId"><br>
 PASSWORD:<input type="password" name="password"><br>
 <input type="submit" value="login">
 <div style="color : #FF0000">${loginErrorMessage}</div>
 </form>
</body>
</html>