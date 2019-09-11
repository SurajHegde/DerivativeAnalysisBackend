<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.pojo.Holding"%>
    <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User holdings</title>
<%
List<Holding> userHolding = (List<Holding>) request.getAttribute("userHoldings");
%>
<table>
	<c:foreach items="${userHolding}" var="lst">
		<tr>
		<td><c:out value="${lst.avgPrice}"></c:out> </td>
		
		</tr>
	
	</c:foreach>

</table>
</head>
<body>

</body>
</html>