<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Directives Example</title>
</head>
<body>

<h1>Directives Example</h1>

<p>This is a simple example of using JSP directives.</p>

<%
    String message = "Body conetent";
    out.println("<p>" + message + "</p>");
%>

<%@ include file="footer.jsp" %>

</body>
</html>
