<html>
<head>
    <title>Implicit Objects in JSP</title>
</head>
<body>
    <h1>Implicit Objects in JSP</h1>
    
    <%
        String name = "John";
        out.println("Request Method: " + request.getMethod() + "<br>");
        out.println("Context Path: " + request.getContextPath() + "<br>");
        out.println("Servlet Path: " + request.getServletPath() + "<br>");
        out.println("Session ID: " + session.getId() + "<br>");
        out.println("Application Scope Attribute: " + application.getAttribute("someAttribute") + "<br>");
        out.println("Client's IP Address: " + request.getRemoteAddr() + "<br>");
        out.println("Name: " + name + "<br>");
    %>
    
</body>
</html>
