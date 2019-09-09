<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.pojo.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<h3 align = "center">Welcome <c:out value="${sessionScope.name}"><br></c:out>
<c:out value="${sessionScope.email}"></c:out></h1>
<a href="GetUserHoldings" method = "get">User holdings</a>

</body>
</html>