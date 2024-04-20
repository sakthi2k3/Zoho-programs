<!DOCTYPE html>
<html>
<head>
    <title>Session Tracking Example</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            width: 50%;
            margin: 0 auto;
        }
        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        #output {
            margin-top: 20px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Session Tracking Example</h2>
        <%-- Check if a session already exists --%>
        <% if (session.isNew()) { %>
            <p>Welcome! This is your first visit.</p>
        <% } else { %>
            <p>Welcome back! Your session ID is: <%= session.getId() %></p>
            <p>You have visited this page <%= session.getAttribute("visitCount") %> times.</p>
        <% } %>

        <%-- Increment visit count and store it in session --%>
        <% int visitCount = 1; %>
        <% if (session.getAttribute("visitCount") != null) {
               visitCount = (int) session.getAttribute("visitCount") + 1;
           } %>
        <% session.setAttribute("visitCount", visitCount); %>

        <form action="" method="post">
            <input type="submit" name="reset" value="Reset Visit Count">
        </form>

        <%-- Handle form submission to reset visit count --%>
        <% if (request.getParameter("reset") != null) {
               session.invalidate();
               response.sendRedirect(request.getRequestURI());
           } %>
    </div>
</body>
</html>
